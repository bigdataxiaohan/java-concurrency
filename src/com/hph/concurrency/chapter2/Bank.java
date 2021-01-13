package com.hph.concurrency.chapter2;

public class Bank {
    public static void main(String[] args) {
        TicketWindow t1 = new TicketWindow("一号柜台");
        TicketWindow t2 = new TicketWindow("二号柜台");
        TicketWindow t3 = new TicketWindow("三号柜台");
        t1.start();
        t2.start();
        t3.start();
    }
}
