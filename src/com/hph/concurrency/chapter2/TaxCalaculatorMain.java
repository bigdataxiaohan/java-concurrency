package com.hph.concurrency.chapter2;

import javax.lang.model.element.VariableElement;

/**
 * @author 46700
 */
public class TaxCalaculatorMain {
    public static void main(String[] args) {

       /* TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d) {
            @Override
            protected double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };


        double calculate = taxCalaculator.calculate();
        System.out.println(calculate);*/

        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d, (s, b) -> s * 0.1 + b * 0.1);

        //SimpleCalaculatorStrategy simpleCalaculatorStrategy = new SimpleCalaculatorStrategy();

        //taxCalaculator.setCalaculatorStrategy( );
        System.out.println(taxCalaculator.calculate());
    }

}
