package com.hph.concurrency.chapter2;

public class TicketWindow extends Thread {

    //staic 在JVM中有自己的空间
    private static final int MAX = 50;
    private final String name;

    private static int index = 1;


    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台:\t" + name + "当前的号码是:\t " + index++);
        }
    }
}
