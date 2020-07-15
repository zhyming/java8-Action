package com.thread.concurrency.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/6 23:05
 */
public class LockWindow {

    public static void main(String[] args) {

        final BooleanLock booleanLock = new BooleanLock();

        Stream.of("T1","T2", "T3", "T4").forEach(
                t -> new Thread(
                        () -> {
                            try {
                                booleanLock.lock(5_000);
                                Optional.of(Thread.currentThread().getName() + "  get the lock monitor.").ifPresent(System.out::println);
                                working();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (Lock.TimeOutException e) {
                                Optional.of(Thread.currentThread().getName() +" "+ e.getMessage()).ifPresent(System.out::println);
                            } finally {
                                booleanLock.unlock();
                            }

                        }, t
                ).start()
        );

        try {
            Thread.sleep(1_00);
            booleanLock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void working(){

        Optional.of(Thread.currentThread().getName() + "  is working...").ifPresent(System.out::println);

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
