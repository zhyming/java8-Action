package com.thread.concurrency.chapter6;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 22:30
 */
public class GracefulCloseThread {

    private static class Worker extends Thread{

        private volatile boolean start = true;

        @Override
        public void run() {
            while(start){

            }
        }

        public void shutdowm(){
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shutdowm();
    }
}
