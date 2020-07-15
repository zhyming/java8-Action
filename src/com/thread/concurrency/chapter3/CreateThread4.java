package com.thread.concurrency.chapter3;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/29 0:20
 */
public class CreateThread4 {

    private static int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(null, null, "测试线程栈大小", 1 << 24){
            @Override
            public void run() {
                try{
                    add(1);
                }catch (Error e){
                    System.out.println(count);
                }

            }
        };

        t1.start();


    }

    private static void add(int i){
        count++;
        add(i ++);
    }
}
