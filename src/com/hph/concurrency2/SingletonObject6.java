package com.hph.concurrency2;

/***
 * 推荐使用这种方式
 */
public class SingletonObject6 {


    private SingletonObject6() {

    }

    private static class InstanceHolder {
        //static 至运行一次 并且保证线程的执行顺序
        private final static SingletonObject6 instance = new SingletonObject6();

    }

    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }
}
