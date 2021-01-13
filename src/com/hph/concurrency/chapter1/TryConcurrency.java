package com.hph.concurrency.chapter1;


public class TryConcurrency {

    public static void main(String[] args) {


        new Thread("READ-Thread") {
            @Override
            public void run() {
                println(Thread.currentThread().getName());
                readFromDataBase();
                super.run();
            }
        }.start();


        Thread t1 = new Thread() {
            @Override
            public void run() {
                println(Thread.currentThread().getName());
                readFromDataBase();
            }
        };

        t1.start();
        //t1.start();
        t1.run();


        new Thread("WRITE-Thread") {
            @Override
            public void run() {
                super.run();
                writeDataToFile();
            }
        }.start();

        /* *//*       for (int i = 0; i < 100; i++) {
            println("Task 1=>" + i);
        }*//*


        //创建线程实例
        Thread t1 = new Thread("Custom-Thread") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    println("Task 1=>" + i);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //启动线程
       // t1.start();
        for (int j = 0; j < 100; j++) {
            println("Task 2 =>" + j);
        }
        System.out.println("所有任务已经完成");*/

     /*   try {
            Thread.sleep(1000 * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    private static void readFromDataBase() {
        //read data from database and handle it.
        try {
            println("Begin read data from db.");
            Thread.sleep(1000 * 30L);
            println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }

    private static void writeDataToFile() {
        try {
            println("Begin write data to file.");
            Thread.sleep(2000 * 20L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }

    private static void println(String message) {
        System.out.println(message);
    }
}
