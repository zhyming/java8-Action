package com.thread.concurrency.chapter4;

import java.util.Optional;

import static java.lang.Thread.sleep;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 15:01
 */
public class ThreadId {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Optional.of("Hello World").ifPresent(System.out::println);
            try {
                sleep(50_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1");
        t.start();
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
