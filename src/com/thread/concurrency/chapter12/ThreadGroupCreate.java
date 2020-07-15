package com.thread.concurrency.chapter12;

import java.util.Arrays;

import static java.lang.Thread.sleep;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/8 23:22
 */
public class ThreadGroupCreate {

    public static void main(String[] args) {

        int a = 12;
        int b = 0;

        new Thread(() -> {
            while (true){
                System.out.println(Thread.currentThread().getName() + " entry...");
                try {
                    sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try{
                    System.out.println(a / b);
                }catch (ArithmeticException e){
                    System.out.println(e);
                }
            }

        }).start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*ThreadGroup tg1 = new ThreadGroup("TG1");

        Thread t1 = new Thread(tg1, "t1"){
            @Override
            public void run() {
               *//* System.out.println(getThreadGroup().getName());
                System.out.println(getThreadGroup().getParent().getName());
                System.out.println(getThreadGroup().getParent().activeCount());*//*
                try {
                    sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();

        ThreadGroup tg2 = new ThreadGroup("TG2");

        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                System.out.println(tg1.getName());

                Thread[] threads = new Thread[tg1.activeCount()];

                tg1.enumerate(threads);

                Arrays.asList(threads).stream().forEach(System.out::println);
            }
        };

        t2.start();*/

//        System.out.println(tg2.getName());
//        System.out.println(tg2.getParent().getName());
//        System.out.println(tg2.getParent().activeCount());


    }
}
