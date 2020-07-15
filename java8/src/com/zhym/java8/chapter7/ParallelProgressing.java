package com.zhym.java8.chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/22 22:06
 */
public class ParallelProgressing {


    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());

        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");

        System.out.println("The best process result(normalAdd) => " + compareBestResult(ParallelProgressing::normalAdd, 1000_000_000) + " MS");

        //System.out.println("The best process result(iterateStream) => " + compareBestResult(ParallelProgressing::iterateStream, 10_000_000) + " MS");

        //System.out.println("The best process result(parallelStream) => " + compareBestResult(ParallelProgressing::parallelStream, 10_000_000) + " MS");

        //System.out.println("The best process result(parallelStream2) => " + compareBestResult(ParallelProgressing::parallelStream2, 10_000_000) + " MS");

        System.out.println("The best process result(parallelStream3) => " + compareBestResult(ParallelProgressing::parallelStream3, 1000_000_000) + " MS");

    }

    private static long compareBestResult(Function<Long, Long> function, long limit){
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i ++){

            long startTimeStamp = System.currentTimeMillis();
            long result = function.apply(limit);
            long duration = System.currentTimeMillis() - startTimeStamp;
            //System.out.println("The result of sum -> " + result);

            if (duration < fastest)
                fastest = duration;

        }
    return fastest;

    }

    private static long iterateStream(long limit){

        return Stream.iterate(1L, i -> i+1).limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream(long limit){

        return Stream.iterate(1L, i -> i+1).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit){

        return Stream.iterate(1L, i -> i+1).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream3(long limit){

        return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);

        //return Stream.iterate(1L, i -> i+1).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long normalAdd(long limit){

        long sum = 0L;
        for (long i = 0; i < limit; i ++){
            sum += i;
        }

        return sum;

    }

}
