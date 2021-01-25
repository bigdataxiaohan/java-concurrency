package com.hph.concurrency.chapter12;

import java.util.Arrays;

public class ThreadGroupAPI {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        tg1.setDaemon(false);
        t1.start();
        Thread.sleep(2_000);
        System.out.println(tg1.isDestroyed());
        //手动回收
        tg1.destroy();
        System.out.println(tg1.isDestroyed());







     /*   ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                try {
                    Thread.sleep(20_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
        //评估活跃线程数
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
        t2.checkAccess();
        // tg1.destroy();   Exception in thread "main" java.lang.IllegalThreadStateException

        System.out.println("==================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        System.out.println("===================");
        tg1.enumerate(ts1, false);
        Arrays.asList(ts1).forEach(System.out::println);
        System.out.println("================---------");
        tg1.enumerate(ts1, true);
        Arrays.asList(ts1).forEach(System.out::println);

        System.out.println(ts1.length);

       ts1= new Thread[10];
       Thread.currentThread().getThreadGroup().enumerate(ts1,true);
        System.out.println(">>>>>>>>>>>>>>>>>>>>.");

        Arrays.asList(ts1).forEach(System.out::println);

*/


        // tg1.interrupt();
    }
}
