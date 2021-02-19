package com.hph.concurrency2.chapter1;

public class SingletonObject4 {
    private static SingletonObject4 instance;

    private SingletonObject4() {

    }
    //double check

    private static SingletonObject4 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject4.instance) {
                if (null == instance) {
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }
}
