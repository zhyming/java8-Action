package com.thread.concurrency.chapter7;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/1 22:46
 */
public class SynchronizedTest {
    private static final Object MOBITOR = new Object();
    public static void main(String[] args) {

        Runnable runnable = () -> {
            while (true){
                synchronized(MOBITOR){
                    try {
                        Thread.sleep(300_000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }
}
