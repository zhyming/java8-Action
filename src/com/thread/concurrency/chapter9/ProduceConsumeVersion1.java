package com.thread.concurrency.chapter9;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/4 23:10
 */
public class ProduceConsumeVersion1 {

    private int index = 1;

    private final Object LOCK = new Object();
    private  void produce(){
        synchronized(LOCK){
            System.out.println("P-->" + index++);

        }
    }

    private  void consume(){
        synchronized(LOCK){
            System.out.println("C-->" + index);
        }
    }

    public static void main(String[] args) {

        ProduceConsumeVersion1 p = new ProduceConsumeVersion1();

        new Thread("P"){
            @Override
            public void run() {
                while (true){
                    p.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while (true){
                    p.consume();
                }
            }
        }.start();
    }
}
