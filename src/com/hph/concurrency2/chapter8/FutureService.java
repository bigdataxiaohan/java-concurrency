package com.hph.concurrency2.chapter8;

public class FutureService {
    public <T> Future<T> submit(final FutureTask<T> task) {

        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
        }).start();
        return asyncFuture;

    }
}
