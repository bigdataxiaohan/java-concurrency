package com.hph.concurrency2.chapter7;

import java.util.stream.IntStream;

 public class ImmutableClient {
    public static void main(String[] args) {
        Person person = new Person("Alex", "GanSu");

        IntStream.range(0, 5).forEach(i -> {
            new UserPersonThread(person).start();
        });

    }
}
