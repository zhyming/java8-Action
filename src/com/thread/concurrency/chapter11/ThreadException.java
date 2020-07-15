package com.thread.concurrency.chapter11;

import sun.awt.windows.ThemeReader;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/7 17:32
 */
public class ThreadException {

    private static int a = 10;

    private static int b = 0;

    public static void main(String[] args) {

        /*Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("main 线程退出！");
        }));*/


        Thread t = new Thread(() -> {


            try {
                Thread.sleep(5_000);
                int result = a / b;

                System.out.println(result);
                System.out.println(Thread.currentThread().getName() + " 线程退出！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1");

        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(thread);
            System.out.println(e);
        });



        t.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
