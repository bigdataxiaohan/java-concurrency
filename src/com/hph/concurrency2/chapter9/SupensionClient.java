package com.hph.concurrency2.chapter9;


public class SupensionClient {
    public static void main(String[] args) throws InterruptedException {


        final RequestQueue queue = new RequestQueue();

        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        Thread.sleep(10_000);
        serverThread.close();
    }
}
