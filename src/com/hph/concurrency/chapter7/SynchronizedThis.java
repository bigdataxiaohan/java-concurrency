package com.hph.concurrency.chapter7;

public class SynchronizedThis {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        new Thread("t1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("t2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock {
    private final Object LOCK = new Object();

    //修饰方法的时候拿到的是this锁 因此当线程中的同一个对象调用使用synchronization 修饰的方法时 会阻塞等待另一个方法执行完
    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
