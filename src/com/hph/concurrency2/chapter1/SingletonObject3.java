package com.hph.concurrency2.chapter1;

public class SingletonObject3 {
    private static SingletonObject3 instance;


    private SingletonObject3() {

    }

    //Check
    private synchronized static SingletonObject3 getInstance() {
        if (null == instance) {
            instance = new SingletonObject3();
        }
        return SingletonObject3.instance;
    }

}
