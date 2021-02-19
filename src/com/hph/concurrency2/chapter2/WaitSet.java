package com.hph.concurrency2.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;


/**
 * 1.所有的对象都会有1哥waitSet  用来存放对象wait之后进入 block之后的状态线程
 * 2.线程呗notify之后不一定立即得到执行
 * 3.线程从wait set 中呗唤醒的
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        IntStream.range(1, 10).forEach(i ->
                new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        synchronized (LOCK) {
                            try {
                                Optional.of(Thread.currentThread().getName() + " will come to wait set ").ifPresent(System.out::println);
                                LOCK.wait();
                                Optional.of(Thread.currentThread().getName() + " will leave to wait set ").ifPresent(System.out::println);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start()
        );
    }
}
