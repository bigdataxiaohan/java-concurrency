package com.hph.concurrency2.chapter9;

import java.util.Random;

public class ServerThread extends Thread {

    private final RequestQueue queue;
    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag) {
            Request request = queue.getRequest();
            if (null == request) {
                System.out.println("Received the empty request. ");
                continue;
            }
            System.out.println("Server ->" + request.getValue());
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
