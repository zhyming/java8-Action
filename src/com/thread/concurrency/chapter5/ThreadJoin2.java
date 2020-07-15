package com.thread.concurrency.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 15:31
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        /*Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 is running...");
                sleep(10_000);
                System.out.println("t1 is done...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.join(5_000);

        Optional.of("All of task finish done.").ifPresent(System.out::println);

        IntStream.range(1, 1000).forEach(i -> {
            System.out.println(Thread.currentThread().getName() + "->" + i);
        });*/

        Thread.currentThread().join();

    }
}
