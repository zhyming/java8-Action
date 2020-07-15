package com.zhym.java8.chapter8;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/22 23:51
 */
public class ForkJoinPoolTest {

    private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {

        System.out.println("result =>" + calc());

        RecursiveTaskTest recursiveTaskTest = new RecursiveTaskTest(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer taskResult = forkJoinPool.invoke(recursiveTaskTest);
        System.out.println("RecursiveTaskTest => " + taskResult);

        RecursiveActionTest recursiveActionTest = new RecursiveActionTest(0, data.length, data);
        forkJoinPool.invoke(recursiveActionTest);
        System.out.println("RecursiveActionTest => " + RecursiveActionTest.AccumulateHelper.getResult());
    }

    private static int calc(){

        int result = 0;

        for (int i = 0; i < data.length; i++){

            result += data[i];
        }

        return result;
    }
}
