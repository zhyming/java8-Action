package com.zhym.java8.chapter11;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/25 23:54
 */
public class CompletableFutureAction {

    private static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        }).start();

        System.out.println("=====================is not block======================");
        //Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
        completableFuture.whenComplete((v, t) -> System.out.println(v));
    }

    public static double get(){

        try {
            Thread.sleep(random.nextInt(10_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double value = random.nextDouble();
        System.out.println(value);
        return value;
    }

}
