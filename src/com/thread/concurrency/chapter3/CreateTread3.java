package com.thread.concurrency.chapter3;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/5/29 0:15
 */
public class CreateTread3 {

    private static int index = 1;
    public static void main(String[] args) {
        add(index);
    }

    private static void add(int i){
        add(i++);
    }
}
