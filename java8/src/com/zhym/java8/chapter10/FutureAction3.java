package com.zhym.java8.chapter10;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/25 0:33
 */
public class FutureAction3 {

    public static void main(String[] args) {

        Future<String> future = invoke(() -> {

            try {
                Thread.sleep(10_000);
                return "I am Fished!";
            } catch (InterruptedException e) {
                return "Error";
            }
        });

        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {

                System.out.println("Error");
                cause.printStackTrace();
            }
        });

        System.out.println("-----------------------------------");
        System.out.println(future.get());
        System.out.println(future.get());

    }

    private static <T> Future<T> invoke(Callable<T> callable){

        AtomicReference<T> atomicReference = new AtomicReference<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        Future<T> future = new Future<T>() {

            private Completable<T> completable;

            @Override
            public T get() {
                return atomicReference.get();
            }

            @Override
            public boolean idDone() {
                return atomicBoolean.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };

        Thread t = new Thread(() -> {
            try{
                T value = callable.action();
                atomicReference.set(value);
                atomicBoolean.set(true);
                if (future.getCompletable() != null)
                    future.getCompletable().complete(value);

            }catch(Throwable cause){
                if (future.getCompletable() != null){
                    future.getCompletable().exception(cause);
                }
            }
        });

        t.start();


        return future;
    }

    private interface Future<T>{

        T get();

        boolean idDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {
        T action();
    }

    private interface Completable<T>{

        void complete(T t);

        void exception(Throwable cause);
    }
}
