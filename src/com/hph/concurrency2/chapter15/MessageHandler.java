package com.hph.concurrency2.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {


    private final static Random random = new Random(System.currentTimeMillis());

    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(() -> {
            String value = message.getValue();

            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The message will be handle by " + Thread.currentThread().getName() + "\t" + value);
        });


/*        new Thread(() -> {
            String value = message.getValue();

            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The message will be handle by " + Thread.currentThread().getName());

        }).start();*/

    }

    public void shutdown() {
        ((ExecutorService) executor).shutdown();
    }
}
