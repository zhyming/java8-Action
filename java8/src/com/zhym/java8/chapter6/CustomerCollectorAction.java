package com.zhym.java8.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/21 1:28
 */
public class CustomerCollectorAction {

    public static void main(String[] args) {

        Collector<String, List<String>, List<String>> collector = new ToListCollector<>();

        String[] arrs = new String[]{"qwe", "java5", "zhym good", "lanbda", "collector"};

        List<String> result = Arrays.stream(arrs).filter(a -> a.length() > 5).collect(collector);
        System.out.println(result);
        System.out.println("=================================");
        List<String> result2 = Arrays.asList(arrs).parallelStream().filter(a -> a.length() > 5).collect(collector);
        System.out.println(result2);
    }
}
