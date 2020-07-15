package com.thread.concurrency.chapter7;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/3 22:48
 */
public class SynchronizedThis {

    public static void main(String[] args) {

        ThisLock thisLock = new ThisLock();

        new Thread("T1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();


    }


}

class ThisLock{

    private final Object LOCK = new Object();
    public  void m1(){
        synchronized (LOCK){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void m2(){
        synchronized (LOCK){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
