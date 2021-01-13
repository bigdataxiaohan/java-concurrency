package com.hph.concurrency.chapter2;


//可执行的线程
public class TicketWindowRunnable implements Runnable {
    private int index = 0;
    private final static int MAX = 50;


    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread().getName() + " 的号码是" + index++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
