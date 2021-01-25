package com.hph.concurrency.chapter11;

public class ThreadException {
    private final static int A = 10;
    private final static int B = 0;

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5_000);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //捕获内部线程的异常
        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(e);
            System.out.println(thread);
        });
        t.start();
    }
}
