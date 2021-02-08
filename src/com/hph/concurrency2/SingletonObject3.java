package com.hph.concurrency2;

public class SingletonObject3 {

    /**
     * 解决了多线程先创建多个实例，实现了懒加载
     * 缺点：串行化 性能差
     */
    private static SingletonObject3 instance;

    private SingletonObject3() {
        //empty
    }

    public synchronized static SingletonObject3 getInstance() {
        if (null == instance) {
            instance = new SingletonObject3();
        }
        return SingletonObject3.instance;
    }

}
