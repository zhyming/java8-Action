package com.zhym.java8.chapter11;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/25 23:54
 */
public class CompletableFutureAction3 {

    private static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        service.submit(() -> System.out.println("test...."));

        //service.shutdown();

    }

    private static double get(){

        try {
            Thread.sleep(random.nextInt(10_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return random.nextDouble();
    }

}
