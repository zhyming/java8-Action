package com.thread.concurrency.chapter9;

import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/6 1:31
 */
public class WaitAndSleep {

    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        //m1();

        Stream.of("T1", "T2").forEach(n -> {
            new Thread(n){
                @Override
                public void run() {
                    m2();
                }
            }.start();
        });

        /*new Thread("T2"){
            @Override
            public void run() {
                m2();
            }
        }.start();

        new Thread("T1"){
            @Override
            public void run() {
                m1();
            }
        }.start();*/


    }

    private static void m1(){
        synchronized(LOCK){
            try {
                System.out.println(Thread.currentThread().getName() + " enter。。。");
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void m2(){
        synchronized(LOCK){
            try {
                System.out.println(Thread.currentThread().getName() + " enter。。。");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
