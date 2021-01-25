package com.hph.concurrency.chapter11;

import java.util.Arrays;
import java.util.Optional;

public class Test2 {
    public void test() {
        //调用栈帧跟踪信息
        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(e -> !e.isNativeMethod())
                .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + "," + e.getLineNumber())
                        .ifPresent(System.out::println));
    }
}
