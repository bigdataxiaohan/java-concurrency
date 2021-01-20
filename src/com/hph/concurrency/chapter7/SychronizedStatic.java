package com.hph.concurrency.chapter7;

public class SychronizedStatic {

    //静态代码枷锁时class 锁
    static {
        synchronized (SychronizedStatic.class) {
            try {
                System.out.println("static  " + Thread.currentThread().getName());

                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        System.out.println("m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println("m2  " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //非静态锁 m3
    public static void m3() {
        System.out.println("m3  " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
