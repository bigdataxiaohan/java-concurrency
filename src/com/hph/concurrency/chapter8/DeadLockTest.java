package com.hph.concurrency.chapter8;

public class DeadLockTest {
    //如何避免死锁 避免程序锁层级调用
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread() {

            @Override
            public void run() {
                while (true) {
                    deadLock.m1();
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                while (true)
                    otherService.s2();
            }
        }.start();

    }
}
