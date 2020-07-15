package com.zhym.java8.chapter11;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/27 0:21
 */
public class CompletableFutureApi2 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() ->
        {
            System.out.println(Thread.currentThread().getName() + "running 1.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() ->
        {
            System.out.println(Thread.currentThread().getName() + "running 2.");
            return 2.0d;
        }),() -> System.out.println("id done!"));


        System.out.println("-------------------------------------------------");
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "running 1.");

            return 1.0d;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "running 2.");
            return 2.0d;
        }), v ->  v * 10)
                .thenAccept(System.out::println);




        System.out.println("-------------------------------------------------");



        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "running 1.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1.0d;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "running 2.");
            return 2.0d;
        }), System.out::println);


        System.out.println("-------------------------------------------------");

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "running 1.");

            return 1.0d;
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "running 2.");
            return 2.0d;
        }),() -> System.out.println("done!"));



        System.out.println("-------------------------------------------------");


       /* List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureAction::get))

                .collect(Collectors.toList());
        CompletableFuture[] futures = collect.toArray(new CompletableFuture[collect.size()]);
        CompletableFuture.allOf(futures)
        .thenRun(() -> System.out.println("id done!"));*/

        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureAction::get))

                .collect(Collectors.toList());
        CompletableFuture[] futures = collect.toArray(new CompletableFuture[collect.size()]);
        CompletableFuture.anyOf(futures)
                .thenRun(() -> System.out.println("id done!"));

        Thread.currentThread().join();


    }

}
