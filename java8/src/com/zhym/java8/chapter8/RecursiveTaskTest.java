package com.zhym.java8.chapter8;

import java.util.concurrent.RecursiveTask;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/22 23:57
 */
public class RecursiveTaskTest extends RecursiveTask<Integer> {

    private final int start;

    private final int end;

    private final int[] data;

    private final int limit = 3;

    public RecursiveTaskTest(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {

        if (end - start <= limit){
            int result = 0;
            for (int i = start; i < end; i++){
                result += data[i];
            }

            return result;
        }

        int mid = (end + start)/2;

        RecursiveTaskTest left = new RecursiveTaskTest(start, mid, data);

        RecursiveTaskTest right = new RecursiveTaskTest(mid, end, data);

        right.fork();
        Integer leftResult = right.join();

        Integer rightResult = left.compute();

        return leftResult + rightResult;
    }
}
