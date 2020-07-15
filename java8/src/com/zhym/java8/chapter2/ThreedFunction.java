package com.zhym.java8.chapter2;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/14 0:38
 */
@FunctionalInterface
public interface ThreedFunction<T, U, K, R> {

    R apply(T t, U u, K k);
}
