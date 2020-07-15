package com.zhym.java8.chapter11;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/26 23:53
 */
public class CompletableFutureApi1 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> i + 10)
                .whenComplete((v, t) -> System.out.println(v));


        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> System.out.println(v));


        CompletableFuture.supplyAsync(() ->1)
                .thenApply(i -> Integer.sum(i, 10))
                .thenAccept(System.out::println);


        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i * 10))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);



        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                });


        Thread.sleep(10_000);
    }
}
