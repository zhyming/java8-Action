package com.thread.concurrency.chapter9;

import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/4 23:19
 */
public class ProduceConsumeVersion3 {

    private int index = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;
    private  void produce(){
        synchronized(LOCK){
            System.out.println(Thread.currentThread().getName() + "获得锁执行P");
            while (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            index++;
            System.out.println(Thread.currentThread().getName() + "  P-->" + index);
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    private  void consume(){
        synchronized(LOCK){
            System.out.println(Thread.currentThread().getName() + "获得锁执行C");

            while (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "  C-->" + index);
            isProduced = false;
            LOCK.notifyAll();

        }
    }

    public static void main(String[] args) {

        ProduceConsumeVersion3 p = new ProduceConsumeVersion3();

        Stream.of("P1", "P2", "P3", "P4").forEach(n -> new Thread(n){
            @Override
            public void run() {
                while(true){
                    p.produce();
                }
            }
        }.start());


        Stream.of("C1", "C2").forEach(n -> new Thread(n){
            @Override
            public void run() {
                while(true){
                    p.consume();
                }
            }
        }.start());

    }
}
