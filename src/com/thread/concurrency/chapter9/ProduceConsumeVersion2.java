package com.thread.concurrency.chapter9;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/4 23:19
 */
public class ProduceConsumeVersion2 {

    private int index = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;
    private  void produce(){
        synchronized(LOCK){

            if (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                index++;
                System.out.println("P-->" + index);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    private  void consume(){
        synchronized(LOCK){
            if (isProduced){
                System.out.println("C-->" + index);
                isProduced = false;
                LOCK.notify();
            }else{
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {

        ProduceConsumeVersion2 p = new ProduceConsumeVersion2();

        new Thread("P"){
            @Override
            public void run() {
                while(true){
                    p.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while(true){
                    p.consume();
                }
            }
        }.start();
    }
}
