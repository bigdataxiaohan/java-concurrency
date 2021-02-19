package com.hph.concurrency2.chapter1;

public class SingletonObject2 {
    private static SingletonObject2 instance;

    private SingletonObject2() {

    }

    //多线程下 或出现单例是多个实例 甚至是连个以上的实例
    public static SingletonObject2 getInstance() {
        if (null == instance)
            instance = new SingletonObject2();
        return SingletonObject2.instance;
    }

}
