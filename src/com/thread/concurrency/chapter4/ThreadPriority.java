package com.thread.concurrency.chapter4;

import java.util.Optional;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 15:12
 */
public class ThreadPriority {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
            }
        });
        Thread t3 = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
            }
        });

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();

        t2.start();

        t3.start();
    }




}
