package com.hph.concurrency2.chapter3;

public class VolatileTest2 {
    private static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 50;

    public static void main(String[] args) {

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T1->" + (++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();


        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T2->" + (++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();
    }
}
