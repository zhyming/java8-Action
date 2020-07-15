package com.thread.concurrency.chapter6;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 22:38
 */
public class Graceful2CloseThread {
    public static class Worker extends Thread{

        @Override
        public void run() {
            while (true){
                if(isInterrupted())
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();

    }
}
