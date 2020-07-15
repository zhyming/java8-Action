package com.thread.concurrency.chapter6;

import sun.awt.windows.ThemeReader;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 22:45
 */
public class ForceCloseThread {
    public static class Worker extends Thread{

        private boolean flag = true;

        @Override
        public void run() {
            while (flag){

            }
        }
    }

    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();

        long start = System.currentTimeMillis();

        threadService.execute(() -> {
            while (true){

            }
            /*try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        });

        threadService.shutDown(5_000);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
