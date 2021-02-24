package com.hph.concurrency2.chapter17;

import sun.dc.pr.PRError;

import java.util.Arrays;

public class Channel {
    private final static int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int head;
    private int tail;
    private int count;
    private final WorkerThread[] workerPool;

    public Channel(int workers) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[workers];
        this.init();

    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWork() {
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }
}
