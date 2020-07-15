package com.thread.concurrency.chapter10;

import java.util.Optional;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/6 23:28
 */
public class SynchronizedProblem {

    public static void main(String[] args) {
        new Thread(() -> begin(), "T1").start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> begin(), "T2");
        t2.start();

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();

        Optional.of(t2.isAlive()).ifPresent(System.out::println);
    }

    private static synchronized void begin(){
        Optional.of(Thread.currentThread().getName() + " entry...").ifPresent(System.out::println);
        while (true){

        }
    }
}
