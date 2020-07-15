package com.thread.concurrency.chapter12;

import java.util.Arrays;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/8 23:45
 */
public class ThreadGroupAPI {

    public static void main(String[] args) {


        ThreadGroup tg1 = new ThreadGroup("TG1");

        Thread t1 = new Thread(tg1, "t1"){
            @Override
            public void run() {
               /* System.out.println(getThreadGroup().getName());
                System.out.println(getThreadGroup().getParent().getName());
                System.out.println(getThreadGroup().getParent().activeCount());*/
                try {
                    sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();

        //ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");

        /*Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {

                try {
                    sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };*/

        //t2.start();

       /* System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());

        *//*tg2.checkAccess();
        tg2.destroy();*//*

        Thread[] threads = new Thread[tg1.activeCount()];
        tg1.enumerate(threads);

        Arrays.asList(threads).forEach(System.out::println);

        System.out.println("=================================");
        threads = new Thread[tg1.activeCount()];
        tg1.enumerate(threads, true);
        Arrays.asList(threads).forEach(System.out::println);*/

        //tg1.interrupt();
        tg1.setDaemon(true);
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tg1.isDestroyed());
        /*while (!tg1.isDestroyed()){
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("剩余活跃线程数 -> " + tg1.activeCount());
        }*/

        //System.out.println("mian done");
    }

}
