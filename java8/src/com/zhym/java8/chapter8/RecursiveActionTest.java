package com.zhym.java8.chapter8;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/23 0:25
 */
public class RecursiveActionTest extends RecursiveAction {

    private final int start;

    private final int end;

    private final int[] data;

    private final int limit = 3;

    public RecursiveActionTest(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {

        if (end - start <= limit){
            //int result = 0;
            for (int i = start; i < end; i++){
                //result += data[i];
                AccumulateHelper.accumulate(data[i]);
            }

            //return result;
        }else{

            int mid = (start + end) / 2;

            RecursiveAction left = new RecursiveActionTest(start, mid, data);
            RecursiveAction right = new RecursiveActionTest(mid, end, data);

            left.fork();
            right.fork();

            left.join();
            right.join();

        }

    }

    static class AccumulateHelper{

        private static final AtomicInteger result = new AtomicInteger(0);

        private static void accumulate(int value){

            result.getAndAdd(value);
        }

        public static int getResult(){
            return result.get();

        }

        private void reset(){

            result.set(0);
        }
    }
}
