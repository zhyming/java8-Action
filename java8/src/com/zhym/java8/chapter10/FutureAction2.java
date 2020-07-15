package com.zhym.java8.chapter10;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/25 0:06
 */
public class FutureAction2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10_000);
                return "I am Fished!";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println("------------------------------------------");
        /*try {
            String value = future.get(10, TimeUnit.MICROSECONDS);
            System.out.println(value);
        }catch (ExecutionException|InterruptedException|TimeoutException  e){
            System.out.println(e);
        }*/
        while(!future.isDone()){
            Thread.sleep(10);
        }
        String value = future.get(10, TimeUnit.MICROSECONDS);
        System.out.println(value);
        System.out.println("=========================================");
        executorService.shutdown();
    }

}
