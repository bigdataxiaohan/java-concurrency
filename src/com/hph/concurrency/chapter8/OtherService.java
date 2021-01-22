package com.hph.concurrency.chapter8;

public class OtherService {

    private final Object LOCK = new Object();


    private DeadLock deadLock;
    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }


    public void s1() {
        synchronized (LOCK) {
            System.out.println("s1 =============>");
        }
    }


    public void s2 () {
        synchronized (LOCK) {
            System.out.println("s1 =============>");
        }
    }
}
