package com.thread.concurrency.chapter4;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 14:34
 */
public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                Thread t1 = new Thread(){
                    @Override
                    public void run() {
                        System.out.println("子线程开始执行....");
                        try {
                            sleep(30_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("子线程结束执行....");
                    }
                };
                t1.setDaemon(true);
                t1.start();
                System.out.println("t线程开始执行...");
                try {
                    sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t线程结束执行...");
            }
        };

        //t.setDaemon(true);
        t.start();

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main线程结束执行...");
    }
}
