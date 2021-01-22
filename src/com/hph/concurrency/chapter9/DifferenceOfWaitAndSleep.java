package com.hph.concurrency.chapter9;


import java.util.stream.Stream;

/**
 * 1.sleep是thread方法 wait 是bject方法
 * 2.sleep不释放对象锁 wait会释放 会把monitor添加到waitng queue
 * 3.sleep不依赖synchronized  wait必须要定义synchronized同步
 * 4.sleep不需要被唤醒 wait 需要，但是wait(10) 除外
 */
public class DifferenceOfWaitAndSleep {
    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("t1", "t2").forEach(names ->
                new Thread(names) {
                    @Override
                    public void run() {
                        DifferenceOfWaitAndSleep.m1();
                    }
                }.start());
        //m2();
    }

    public static void m1() {


        synchronized (LOCK) {
            try {

                System.out.println("The Thread" + Thread.currentThread().getName() + " enter . ");

                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2() {
        synchronized (LOCK) {
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
