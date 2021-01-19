package com.hph.concurrency.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 is running");
            try {
                Thread.sleep(10_000);
                System.out.println("t1 is done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Optional.of("All of tasks finish done.").ifPresent(System.out::println);
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-" + i));
    }
}
