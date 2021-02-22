package com.hph.concurrency2.chapter11;

import java.util.stream.IntStream;

public class ContextTest {
    public static void main(String[] args) {
        final ExecutionTask executionTask = new ExecutionTask();
        IntStream.range(1, 5).forEach(i -> {
            new Thread(new ExecutionTask()).start();
        });
    }
}
