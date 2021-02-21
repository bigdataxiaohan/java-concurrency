package com.hph.concurrency2.chapter10;

public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return "DEFAULT Alex";
        }
    };

    //JVM start main thread
    public static void main(String[] args) throws InterruptedException {

//        threadLocal.set("Alex");
        Thread.sleep(1_000);
        String value = threadLocal.get();
        System.out.println(value);
    }

}
