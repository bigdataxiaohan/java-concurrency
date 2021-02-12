package com.hph.concurrency2;


/**
 * Double check 版本
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private Object obj;

    private SingletonObject4() {
        int i = 0;
        int j = 10;

        //i=10;j=10;
    }

    //可能会引起空指针一场 happen before 指令重拍序
    public static SingletonObject4 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    //堆内存开辟空间
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }
}
