package com.hph.concurrency2.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 并发线程做事情 要等到所有的线程结束工作 再去做下面的事情
 */
public class JDKCountDown {


    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        //The firest phase

        final CountDownLatch latch = new CountDownLatch(5);

        IntStream.rangeClosed(1, 5).forEach(i -> {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\tis working .");
                try {
                    Thread.sleep(random.nextInt(1_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }, String.valueOf(i)).start();
        });
        latch.await();
        //The send phase
        System.out.println("多线程任务全部结束，准备第二阶段任务");
        System.out.println("..............................");
        System.out.println("FINISH");


    }
}
