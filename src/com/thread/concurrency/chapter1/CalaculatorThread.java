package com.thread.concurrency.chapter1;

public class CalaculatorThread
{
    private static  final Integer maxIndex = 50;

    private String name;

    private static Integer index = 1;


    public CalaculatorThread(String name){
        this.name = name;
    }

    public void startCalc(){
        new Thread(name){
            @Override
            public void run() {
                while ( index <= maxIndex){
                    System.out.printf("%s取得第%d号\n", Thread.currentThread().getName(), index ++);
                    try {
                        Thread.sleep(1000 * 2L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }

   /* protected void runCalc(){

    }*/



    public static void main(String[] args) {

        CalaculatorThread c1 = new CalaculatorThread("用户一");

        CalaculatorThread c2 = new CalaculatorThread("用户二");

        CalaculatorThread c3 = new CalaculatorThread("用户三");

        c1.startCalc();
        c2.startCalc();
        c3.startCalc();
    }


}
