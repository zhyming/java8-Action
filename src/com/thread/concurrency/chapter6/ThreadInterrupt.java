package com.thread.concurrency.chapter6;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 21:31
 */
public class ThreadInterrupt {

    private static final Object monitor = new Object();

    public static void main(String[] args) {

        /*Thread t = new Thread(){
            @Override
            public void run() {
                while(true){

                    synchronized(monitor){
                        try {
                            monitor.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        };
        t.start();

        Thread.sleep(1_000);

        System.out.println(t.isInterrupted());

        t.interrupt();

        System.out.println(t.isInterrupted());*/

        /*Thread t = new Thread(() -> {
           while (true){
               synchronized(monitor){
                   try {
                       monitor.wait(10);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                       System.out.println(Thread.interrupted());
                   }
               }
           }
        });*/

        Thread main = Thread.currentThread();

        Thread t = new Thread(){
            @Override
            public void run() {
                while(true){

                }
            }
        };
        t.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                main.interrupt();

                System.out.println("Interrupted");
            }
        };

        t2.start();

        try{
            t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }



    }


}
