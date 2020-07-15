package com.thread.concurrency.chapter6;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 22:50
 */
public class ThreadService {

    private Thread executeThread;

    private static  boolean finish = false;

    public void execute(Runnable task){
        this.executeThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();

                try {
                    runner.join();
                    finish = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        executeThread.start();

    }

    public void shutDown(long mills){
        long currentTime = System.currentTimeMillis();

        while (!finish){
            if((System.currentTimeMillis() - currentTime) >= mills){
                System.out.println("任务超时，需要手动结束！");
                executeThread.interrupt();
            }

            try {
                executeThread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
    }
}
