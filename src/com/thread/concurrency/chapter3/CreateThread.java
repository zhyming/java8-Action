package com.thread.concurrency.chapter3;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/28 23:28
 */
public class CreateThread {

    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();

        Thread t2 = new Thread("我自己有名字");
        t2.start();

        Thread t3 = new Thread();
        t3.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());
        System.out.println(t3.getName());

        Thread t4 = new Thread(() -> {
            System.out.println("Runnable 逻辑");
        });

        t4.start();
    }


}

