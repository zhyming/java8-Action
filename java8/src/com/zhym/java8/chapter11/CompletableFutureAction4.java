package com.zhym.java8.chapter11;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/25 23:54
 */
public class CompletableFutureAction4 {

    private static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        /*CompletableFuture.supplyAsync(CompletableFutureAction4::get, service)
                .thenApply(CompletableFutureAction4::multiply)
                .whenComplete((v, t) ->
                    Optional.ofNullable(v).ifPresent(System.out::println)
                );*/

       // System.out.println("=====================is not block======================");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Double> result = list.stream().map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), service))
                .map(future -> future.thenApply(CompletableFutureAction4::multiply))
                .map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result);

    }

    private static double multiply(double value){
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return value * 10;
    }

    private static double get(){

        try {
            Thread.sleep(random.nextInt(10_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double value = random.nextDouble();
        System.out.println(value);
        return value;
    }

    public static double queryProduction(int i){
        return get();
    }

}
