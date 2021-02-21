package com.hph.concurrency2.chapter8;

public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {
/*
        String result = get();
        System.out.println(result);*/
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        });
        System.out.println("=============");
        System.out.println("do other thing.  ");
        Thread.sleep(1_000);
        System.out.println("=============");

        System.out.println(future.get());

    }

    private static String get() throws InterruptedException {
        Thread.sleep(10_000);
        return "FINISH";
    }
}
