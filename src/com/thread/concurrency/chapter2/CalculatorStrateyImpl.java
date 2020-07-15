package com.thread.concurrency.chapter2;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/27 0:40
 */
public class CalculatorStrateyImpl implements CalculatorStratey {

    private static final double SALARY_RATE = 0.1;
    private static final double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
