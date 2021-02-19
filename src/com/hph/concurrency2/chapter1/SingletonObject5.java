package com.hph.concurrency2.chapter1;

public class SingletonObject5 {


    //无法保证原子性 但是保证 可见性 和有序性 严格遵循 volatile效率和性能会不好
    private static volatile SingletonObject5 instance;

    private SingletonObject5() {

    }

    //double check
    private static SingletonObject5 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject5.class) {
                if (null == instance) {
                    instance = new SingletonObject5();
                }
            }
        }
        return SingletonObject5.getInstance();
    }
}
