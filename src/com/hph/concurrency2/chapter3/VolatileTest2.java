package com.hph.concurrency2.chapter3;

public class VolatileTest2 {
    private volatile static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 50;

    public static void main(String[] args) {

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T1->" + (++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } }
        }, "READER").start();


        new Thread(() -> {

            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update The value update to [%d]\n", ++localValue);
                INIT_VALUE = localValue;

            }
        }, "UPDATER").start();
    }
}
