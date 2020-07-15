package com.thread.concurrency.chapter7;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/1 23:22
 */
public class SynchronizedRunnable implements Runnable{
    private static int index = 1;

    private static final int MAX = 500;

    private final Object monitor = new Object();

    //不设定monitor 锁是this
    @Override
    public void run() {

        while (true){
            //synchronized(monitor){
                if(ticked()){
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "用户取得号数:" + index ++);

           // }
        }
    }

    private synchronized boolean ticked(){
        if(index > MAX){
            return true;
        }
        System.out.println(Thread.currentThread().getName() + "用户取得号数:" + index ++);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
