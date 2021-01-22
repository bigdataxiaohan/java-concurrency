package com.hph.concurrency.chapter9;

import java.util.stream.Stream;

public class ProducerConsumerVersion2 {
    private int i = 0;
    final private Object LOCK = new Object();
    private volatile boolean isProduce = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->" + i);
                LOCK.notify();
                isProduce = true;
            }
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            if (isProduce) {
                System.out.println("C->" + i);
                //通知消费掉了
                LOCK.notify();
                isProduce = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        ProducerConsumerVersion2 pc = new ProducerConsumerVersion2();
        Stream.of("P1", "P2").forEach(n -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                    }

                }
            }.start();
        });

        Stream.of("C1", "C2").forEach(i -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        pc.consumer();
                    }
                }
            }.start();
        });


    }
}
