package com.hph.concurrency2.chapter13;

import java.util.Random;

public class ConsumerThread extends Thread {
    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());


    public ConsumerThread(MessageQueue messageQueue, int seq) {
        super("Consumer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take message " + message.getData());

                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

        }

    }


}
