package com.hph.concurrency.chapter9;


/**
 * 通知机制的综合应用
 */

import java.util.*;

public class CaptureService {


    final private static LinkedList<Control> CONTROLS = new LinkedList<>();

    private final static int MAX_WORKER = 1;

    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });
        worker.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished ").ifPresent(System.out::println);

    }

    public static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("The worker [" + Thread.currentThread().getName() + "] BEGIN capture data").ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > MAX_WORKER) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }
            Optional.of("The worker +[" + Thread.currentThread().getName() + "] is working").ifPresent(System.out::println);;
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS) {
                Optional.of("The worker +[" + Thread.currentThread().getName() + "] End capture date").ifPresent(System.out::println);;
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();

            }

        }, name);
    }

    private static class Control {

    }
}
