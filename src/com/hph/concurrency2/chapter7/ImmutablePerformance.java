package com.hph.concurrency2.chapter7;

public class ImmutablePerformance {

    public static void main(String[] args) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        //  ImmutableTest  12825
        //synchronized    12720
        SyncObj syncObj = new SyncObj();
        syncObj.setName("Alex");

        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    for (int l = 0; l < 1000000; l++) {
                        System.out.println(Thread.currentThread().getName() + " " + syncObj.toString());
                    }

                }
            };
            t1.start();
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    for (int l = 0; l < 1000000; l++) {
                        System.out.println(Thread.currentThread().getName() + " " + syncObj.toString());
                    }

                }
            };
            t2.start();
            t1.join();
            t2.join();

        }

        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time " + (endTimestamp - startTimestamp));

    }
}

final class ImumutableObj {
    private final String name;


    ImumutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}

class SyncObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }


    @Override
    public synchronized String toString() {
        return "[" + name + "]";
    }

}
