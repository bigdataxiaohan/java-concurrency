package com.hph.concurrency.chapter10;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {


    //The initValue is true indicated the lock hav be get
    //The initValye is false indicated the lock us free (other thread can get this );
    private boolean initValue;


    private Collection<Thread> blockedthreadCollection = new ArrayList<>();

    //判断当前线程
    private Thread currentThread;


    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedthreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedthreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0)
            lock();

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0)
                throw new TimeOutException("Time out");
            blockedthreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
        }
        //抢到锁
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {

        if (Thread.currentThread() == currentThread) {
            //释放锁
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + " released the lock monitor .").ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override

    public Collection<Thread> getBlockedThread() {
        //返回只读的集合
        return Collections.unmodifiableCollection(blockedthreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedthreadCollection.size();
    }
}
