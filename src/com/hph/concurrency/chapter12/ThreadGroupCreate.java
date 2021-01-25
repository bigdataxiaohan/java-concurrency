package com.hph.concurrency.chapter12;

import java.util.Arrays;

public class ThreadGroupCreate {
    public static void main(String[] args) {
        // use the name


        ThreadGroup tg1 = new ThreadGroup("TG1");

        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent());
                        System.out.println(getThreadGroup().getParent().activeCount());
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        new Thread("TG2") {
            @Override
            public void run() {

                System.out.println("访问父类方法");
                System.out.println(tg1.getName());

                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);

                Arrays.asList(threads).forEach(System.out::println);
            }
        }.start();


        System.out.println(tg2.getName());
        System.out.println(tg2.getParent());


        //  System.out.println(t1.getThreadGroup().getParent().getName());


        //use the parent and group name
        //main线程也是一个线程   在run方法中调用了main方法
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getName());
    }
}
