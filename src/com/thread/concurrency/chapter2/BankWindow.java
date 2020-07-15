package com.thread.concurrency.chapter2;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/26 23:58
 */
public class BankWindow {
    public static void main(String[] args) {
        TicketWindow t1 = new TicketWindow("一号");
        t1.start();

        TicketWindow t2 = new TicketWindow("二号");
        t2.start();

        TicketWindow t3 = new TicketWindow("三号");
        t3.start();

    }
}
