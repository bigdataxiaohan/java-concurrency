package com.hph.concurrency2.chapter1;

import java.util.stream.IntStream;

public class SingletonObject1 {
    //单例的设计模式


    /**
     * can‘t lazy load
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {

    }

    public static SingletonObject1 getInstance() {
        return instance;
    }


    //懒汉模式


    public static void main(String[] args) {
        IntStream.range(1, 10000)
                .forEach(i -> new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        System.out.println(SingletonObject1.getInstance());
                    }
                }.start());
    }

}
