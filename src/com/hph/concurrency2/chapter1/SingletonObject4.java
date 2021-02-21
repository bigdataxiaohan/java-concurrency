package com.hph.concurrency2.chapter1;

public class SingletonObject4 {


    //无法保证原子性 但是保证 可见性 和有序性 严格遵循 volatile效率和性能会不好
    private static volatile SingletonObject4 instance;

    private SingletonObject4() {

    }

    //double check
    private static SingletonObject4 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.getInstance();
    }
}
