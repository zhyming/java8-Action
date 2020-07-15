package com.thread.concurrency.chapter5;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 15:22
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            });
        });

        t1.start();
        Thread t2 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            });
        });

        t2.start();
        Thread t3 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            });
        });

        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(1, 1000).forEach(i -> {
            System.out.println(Thread.currentThread().getName() + "->" + i);
        });
    }
}
