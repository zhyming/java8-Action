package com.thread.concurrency.chapter3;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/28 23:41
 */
public class CreateThread2 {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000 * 10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println(t.getThreadGroup());

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        System.out.println(threadGroup.activeCount());

        Thread[] threads =  new Thread[threadGroup.activeCount()];

        threadGroup.enumerate(threads);

        for (Thread thread : threads){
            System.out.println(thread.getName());
        }

        //System.out.println(Thread.currentThread().getThreadGroup());
    }
}
