package com.hph.concurrency.chapter13;


import com.sun.org.apache.regexp.internal.RE;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 1.任务队列
 * 2.拒绝策略(抛出异常，直接丢弃，阻塞，临时队列)
 * 3.init(min)
 * 4.active
 * 5.max 限制
 * min >=active >=max
 */
public class SimpleThreadPool extends Thread {
    private int size;
    private final int queueSize;
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    //自增
    private static volatile int seq = 0;


    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private final DiscardPolicy discardPolicy;
    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This Task . ");
    };


    private volatile boolean destroy = false;

    private int min;
    private int max;
    private int active;

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean iSdestroy() {
        return this.destroy;
    }


    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }


    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < this.min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }


    public void submit(Runnable runnable) {
        //对状态进行判断
        if (destroy)
            throw new IllegalStateException("The thread pool already destroy and not allow submit task.");
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize)
                discardPolicy.discard();
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000);
                //需要扩种
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to active.");
                    //第一次扩容
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }
                //说明但任务已经完成完了 但是线程还在占据着资源需要释放
                if (TASK_QUEUE.isEmpty() && size > active) {
                    System.out.println("==============Reduce============");
                    //防止在submit的时候 其他的线程去做修改
                    synchronized (TASK_QUEUE) {
                        int releaseSize = size - active;

                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0)
                                break;
                            WorkerTask task = it.next();
                            //
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        //判断 当前线程的大小
        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkerTask task : THREAD_QUEUE) {
                if (task.getTaskState() == TaskState.BLOCK) {
                    //中断 线程退出
                    task.interrupt();
                    //关闭
                    task.close();
                    initVal--;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        this.destroy = true;
        System.out.println("The thread pool; disposed.");
    }

    //定义线程状态
    private enum TaskState {
        FREE, RUNNING, BLOCK, DEAD;
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    //拒绝策略
    public interface DiscardPolicy {
        public void discard() throws DiscardException;
    }


    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }


        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCK;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed.");
                            break OUTER;
                        }
                    }
                    runable = TASK_QUEUE.removeFirst();
                }
                if (runable != null) {
                    taskState = TaskState.RUNNING;
                    runable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        //  SimpleThreadPool threadPool = new SimpleThreadPool(6, 10, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        SimpleThreadPool threadPool = new SimpleThreadPool();

        for (int i = 0; i < 140; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable  be serviced by " + Thread.currentThread() + " start.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by " + Thread.currentThread() + " finished.");
            });
        }
        Thread.sleep(10000);
        threadPool.shutdown();


/*        Thread.sleep(1000);
        threadPool.shutdown();
        threadPool.submit(() -> System.out.println("======="));*/
/*
        IntStream.rangeClosed(0, 40)
                .forEach(i -> {
                    threadPool.submit(() -> {
                        System.out.println("The runnable " + i + "  be serviced by " + Thread.currentThread() + " start.");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("The runnable " + i + "  be serviced by " + Thread.currentThread() + " finished.");

                    });
                });
*/

    }

}
