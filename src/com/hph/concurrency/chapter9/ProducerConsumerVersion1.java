package com.hph.concurrency.chapter9;

public class ProducerConsumerVersion1 {

    /**
     * 线程之间未进行通讯
     */
    private int i = 1;
    final private Object LOCK = new Object();

    private void produce() {

        synchronized (LOCK) {
            System.out.println("P->" + (i++));
        }
    }

    private void comsumer() {
        synchronized (LOCK) {
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion1 pc = new ProducerConsumerVersion1();
        new Thread("P") {
            @Override
            public void run() {
                while (true)
                    pc.produce();

            }
        }.start();
        new Thread("C") {
            @Override
            public void run() {
                while (true)
                    pc.comsumer();
            }
        }.start();
    }
}
