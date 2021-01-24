package com.hph.concurrency.chapter10;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {


    //The initValue is true indicated the lock hav be get
    //The initValye is false indicated the lock us free (other thread can get this );
    private boolean initValue;

    public BooleanLock() {
        this.initValue = false;
    }


    private Collection<Thread> blockedthreadCollection = new ArrayList<>();

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedthreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedthreadCollection.remove(Thread.currentThread());
        this.initValue = true;
    }

    @Override
    public void locks(long mills) throws InterruptedException, TimeOutException {

    }

    @Override
    public synchronized void unlock() {

        //释放锁
        this.initValue = false;
        Optional.of(Thread.currentThread().getName() + " released the lock monitor .").ifPresent(System.out::println);
        this.notifyAll();
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
