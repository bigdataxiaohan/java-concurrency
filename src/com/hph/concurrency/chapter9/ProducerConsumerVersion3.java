package com.hph.concurrency.chapter9;

import java.util.stream.Stream;

public class ProducerConsumerVersion3 {
    private int i = 0;
    final private Object LOCK = new Object();
    private volatile boolean isProduce = false;

    public void produce() {
        synchronized (LOCK) {
            while (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P->" + i);
            LOCK.notifyAll();
            isProduce = true;

        }
    }

    public void consumer() {

        synchronized (LOCK) {
            while (!isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C->" + i);
            //通知消费掉了
            LOCK.notifyAll();
            isProduce = false;
        }
    }

    public static void main(String[] args) {

        ProducerConsumerVersion3 pc = new ProducerConsumerVersion3();
        Stream.of("P1", "P2","P3").forEach(n -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        });

        Stream.of("C1", "C2","C3","C4").forEach(i -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        pc.consumer();
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }.start();
        });


    }
}
