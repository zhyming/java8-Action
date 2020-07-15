package com.thread.concurrency.chapter2;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/27 0:26
 */
public class TaxMain {
    public static void main(String[] args) {
        /*TaxCalculator taxCalculator = new TaxCalculator(10000, 2000){
            @Override
            protected double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.2;
            }
        };

        double tax = taxCalculator.calculate();

        System.out.println("扣税:" + tax);*/

        TaxCalculator taxCalculator = new TaxCalculator(10000, 3000);

        CalculatorStratey calculatorStratey = new CalculatorStrateyImpl();

        taxCalculator.setCalculatorStratey(calculatorStratey);

        double tax = taxCalculator.calculate();
        System.out.println("扣税:" + tax);
    }
}
