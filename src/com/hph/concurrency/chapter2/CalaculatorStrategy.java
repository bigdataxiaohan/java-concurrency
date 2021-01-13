package com.hph.concurrency.chapter2;


@FunctionalInterface
public interface CalaculatorStrategy {
    public double calculate(double salary, double bonus);
}
