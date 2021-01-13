package com.hph.concurrency.chapter2;

import javax.swing.plaf.SliderUI;

public class BankVersion2 {
    private final static int MAX = 50;

    public static void main(String[] args) {
        // final TicketWindowRunnable ticktWindow = new TicketWindowRunnable();

        final Runnable ticktWindow = () -> {
            int index = 1;
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + " 的号码是:" + index++);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(ticktWindow, "一号窗口");
        Thread t2 = new Thread(ticktWindow, "二号窗口");
        Thread t3 = new Thread(ticktWindow, "三号窗口");
        Thread t4 = new Thread(ticktWindow, "四号窗口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
