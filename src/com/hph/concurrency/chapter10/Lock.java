package com.hph.concurrency.chapter10;

import java.util.Collection;

public interface Lock {
    public class TimeOutException extends Exception {

        public TimeOutException(String message) {
            super(message);
        }
    }


    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();


}
