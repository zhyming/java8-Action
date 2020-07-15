package com.thread.concurrency.chapter2;

/**
 * TODO
 *
 * @Author Administrator
 * @Date 2020/5/26 23:49
 */
public class TicketWindow extends Thread{

    private final String name;

    private static final Integer Max = 50;

    private static Integer index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        while (index <= Max){
            System.out.println(name + "用户取得号数:" + index++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
