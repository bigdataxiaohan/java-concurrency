package com.hph.concurrency2.chapter14;

public class CountDown {

    private final int total;

    private int counter;

    public CountDown(int total) {
        this.total = total;
    }

    void down() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }
}
