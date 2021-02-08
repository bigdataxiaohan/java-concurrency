package com.hph.concurrency2;

public class SingletonObject2 {

    //懒汉模式
    private static SingletonObject2 instance;

    private SingletonObject2() {

    }

    public static SingletonObject2 getInstance() {
        if (null == instance)
            instance = new SingletonObject2();
        return SingletonObject2.instance;
    }

}
