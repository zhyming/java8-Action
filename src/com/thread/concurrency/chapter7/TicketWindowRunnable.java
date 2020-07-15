package com.thread.concurrency.chapter7;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/27 0:09
 */
public class TicketWindowRunnable implements Runnable{

    private static int index = 1;

    private static final int MAX = 500;

    private final Object monitor = new Object();

    @Override
    public void run() {

            while (true){
                synchronized(monitor){
                    if(index > MAX){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "用户取得号数:" + index ++);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
