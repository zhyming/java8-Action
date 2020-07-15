package com.zhym.java8.chapter10;

import com.zhym.java8.chapter2.ThreedFunction;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/24 23:23
 */
public class FutureAction {

    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10_000);
                return "I am Finish!";
            } catch (InterruptedException e) {
                return "Error";
            }
        });

        System.out.println(future.get());
       // Optional.ofNullable(future.get()).ifPresent(System.out::println);
        Optional.ofNullable(future.idDone()).ifPresent(System.out::println);

        while(!future.idDone()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Optional.ofNullable(future.idDone()).ifPresent(System.out::println);
    }

    private static <T> T block(Callable<T> callable){
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable){

        AtomicReference<T> atomicReference = new AtomicReference<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        Thread t = new Thread(() -> {
            T value = callable.action();
            atomicReference.set(value);
            atomicBoolean.set(true);

        });

        t.start();

        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return atomicReference.get();
            }

            @Override
            public boolean idDone() {
                return atomicBoolean.get();
            }
        };

        return future;
    }

    private interface Future<T>{

        T get();

        boolean idDone();
    }

    private interface Callable<T> {
        T action();
    }
}
