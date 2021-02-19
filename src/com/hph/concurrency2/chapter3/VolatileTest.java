package com.hph.concurrency2.chapter3;

public class VolatileTest {
    private volatile static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 15;

    public static void main(String[] args) {

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value update to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "READER").start();


        new Thread(() -> {

            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update The value update to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();
    }
}
