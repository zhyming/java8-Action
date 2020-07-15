package com.thread.concurrency.chapter2;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/27 0:13
 */
public class Bank2Window {
    public static void main(String[] args) {
        TicketWindowRunnable t = new TicketWindowRunnable();

        Thread t1 = new Thread(t, "一号");
        Thread t2 = new Thread(t, "二号");
        Thread t3 = new Thread(t, "三号");

        t1.start();
        t2.start();
        t3.start();
    }
}
