package com.thread.concurrency.chapter5;

import java.text.*;

import static java.lang.Thread.sleep;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/31 15:46
 */
public class ThreadJoin3 {

    public static void main(String[] args) throws InterruptedException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1", 10_000L));
        Thread t2 = new Thread(new CaptureRunnable("M2", 20_000L));
        Thread t3 = new Thread(new CaptureRunnable("M3", 30_000L));

        t1.start();
        t2.start();
        t3.start();


        t1.join();
        t2.join();
        t3.join();
        long endTime = System.currentTimeMillis();

        System.out.printf("保存数据开始时间为：%s,结束时间为：%s\n", format.format(startTime), format.format(endTime));
    }

}

class CaptureRunnable implements Runnable{

    private String machineName;

    private long spendTime;

    public CaptureRunnable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        //do something

        try {
            sleep(spendTime);
            System.out.printf("%s 完成抽取任务\n", machineName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public String getResult(){
        return machineName + "finish.";
    }


}
