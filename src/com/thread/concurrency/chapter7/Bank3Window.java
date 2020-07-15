package com.thread.concurrency.chapter7;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/1 23:29
 */
public class Bank3Window {

    public static void main(String[] args) {
        SynchronizedRunnable t = new SynchronizedRunnable();

        Thread t1 = new Thread(t, "一号");
        Thread t2 = new Thread(t, "二号");
        Thread t3 = new Thread(t, "三号");

        t1.start();
        t2.start();
        t3.start();
    }
}
