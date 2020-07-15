package com.zhym.java8.chapter9;

import java.util.concurrent.Callable;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/23 23:30
 */
public class DefaultInAction {

    private static void confuse(String o){
        System.out.println("Object");
    }
    private static void confuse(int[] a){
        System.out.println("int[]");
    }

    public static void main(String[] args) {
        /*A a = () -> 10;

        System.out.println(a.size());
        System.out.println(a.idEmpty());*/
        //confuse(null);
    }

    private interface A{

        int size();

        default boolean idEmpty(){
            return size() == 0;
        }
    }

}
