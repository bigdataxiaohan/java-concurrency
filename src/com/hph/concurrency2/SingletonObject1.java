package com.hph.concurrency2;

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


}
