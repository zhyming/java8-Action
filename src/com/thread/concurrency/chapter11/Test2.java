package com.thread.concurrency.chapter11;

import java.util.Arrays;
import java.util.Optional;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/7 17:40
 */
public class Test2 {

    public void test(){
        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(e -> !e.isNativeMethod())
                .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber())
                        .ifPresent(System.out::println));
    }
}
