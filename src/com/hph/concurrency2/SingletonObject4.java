package com.hph.concurrency2;


/**
 * Double check 版本
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private SingletonObject4() {

    }


    public static SingletonObject4 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }
}
