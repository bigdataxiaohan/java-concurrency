package com.hph.concurrency2.chapter6;


/**
 * ReadWriterLock design  pattern
 * Reader-Writer design pattern
 */
public class ReadWritLockClient {
    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);

        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new WriterWorker(sharedData,"qwertyuiopk").start();
        new WriterWorker(sharedData,"QWERTYUIOPK").start();

    }
}
