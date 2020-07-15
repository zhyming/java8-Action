package com.thread.concurrency.chapter7;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/3 22:59
 */
public class SynchronizedStatic {

    static{
        synchronized(SynchronizedStatic.class){
            try {
                System.out.println("static     " + Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1(){
        try {
            System.out.println("m1   " + Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2(){
        try {
            System.out.println("m2    " + Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3(){
        try {
            System.out.println("m3    " + Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
