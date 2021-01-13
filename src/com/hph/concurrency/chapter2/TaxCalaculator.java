package com.hph.concurrency.chapter2;

public class TaxCalaculator {
    private final double salary;
    private final double bonus;

    private final CalaculatorStrategy calaculatorStrategy;

    public TaxCalaculator(double salary, double bonus, CalaculatorStrategy calaculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calaculatorStrategy = calaculatorStrategy;
    }

    protected double calcTax() {
        return calaculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return this.calcTax();
    }


    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }


}
