package com.thread.concurrency.chapter2;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/27 0:22
 */
public class TaxCalculator
{
    private final double salary;

    private final double bonus;

    private CalculatorStratey calculatorStratey;

    public TaxCalculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }


    protected double calcTax(){
        return calculatorStratey.calculate(this.salary, this.bonus);
    }

    public final double calculate(){
         return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public CalculatorStratey getCalculatorStratey() {
        return calculatorStratey;
    }

    public void setCalculatorStratey(CalculatorStratey calculatorStratey) {
        this.calculatorStratey = calculatorStratey;
    }
}
