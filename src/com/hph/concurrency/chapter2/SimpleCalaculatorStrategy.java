package com.hph.concurrency.chapter2;

public class SimpleCalaculatorStrategy implements CalaculatorStrategy {

    private final static double SALARY_RATE = 0.1;
    private final static double BONUS_RATE = 0.1;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + BONUS_RATE * bonus;
    }
}
