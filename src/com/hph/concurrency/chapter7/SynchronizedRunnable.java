package com.hph.concurrency.chapter7;

public class SynchronizedRunnable implements Runnable {

    private int index = 1;
    //readonly 数据
    private final static int MAX = 500;
    private final Object MONITOR = new Object();


    /**
     * this锁
     */
    @Override
    public void run() {
        while (true) {
            if (ticket())
                break;
        }

    }

    private boolean ticket() {
        //1.getField 读操作 拷贝到当前线程的cache里面去

        synchronized (this) {
            if (index > MAX)
                return true;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //index++ => index+1
            //1. getField index
            //2. index =inde+1
            //3. put field index
            System.out.println(Thread.currentThread() + " 的号码是: " + (index++));
            return false;
        }


    }
}

