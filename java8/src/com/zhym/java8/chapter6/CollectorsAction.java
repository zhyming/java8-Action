package com.zhym.java8.chapter6;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zhym.java8.chapter3.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/18 23:19
 */
public class CollectorsAction {

    public final static List<Dish> menu = Arrays.asList(
            new Dish("pork",false,800,Dish.Type.MEAT),
            new Dish("beef",false,700,Dish.Type.MEAT),
            new Dish("chicken",false,400,Dish.Type.MEAT),
            new Dish("frenchfries",true,530,Dish.Type.OTHER),
            new Dish("rice",true,350,Dish.Type.OTHER),
            new Dish("seasonfruit",true,120,Dish.Type.OTHER),
            new Dish("pizza",true,550,Dish.Type.OTHER),
            new Dish("prawns",false,300,Dish.Type.FISH),
            new Dish("salmon",false,450,Dish.Type.FISH));

    public static void main(String[] args) {
        testAveragingDouble();

        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();

        //testCollectingAndThen2();

        testCounting();

        testGroupingByFunction();

        testGroupingByFunction2();

        testGroupingByFunction3();

        testSummarizingInt();
    }

    private static void testAveragingDouble(){

        System.out.println("testAveragingDouble");

        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingInt(){

        System.out.println("testAveragingInt");

        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingLong(){

        System.out.println("testAveragingLong");

        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testCollectingAndThen(){

        System.out.println("testCollectingAndThen");

        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(Dish::getCalories), a -> "The average calories is -> " + a)))
                .ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingDouble(Dish::getCalories), a -> "The average calories is" + a)))
                .ifPresent(System.out::println);
    }

    private static void testCollectingAndThen2(){

        System.out.println("testCollectingAndThen2");

        List<Dish> meatList = menu.stream().filter(n -> n.getType().equals("MEAT")).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        meatList.add(new Dish("salmon",false,450,Dish.Type.FISH));

        System.out.println(meatList);
    }

    private static void testCounting(){

        System.out.println("testCounting");

        Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
    }

    private static void testGroupingByFunction(){

        System.out.println("testGroupingByFunction");

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
    }

    private static void testGroupingByFunction2(){

        System.out.println("testGroupingByFunction2");

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void testGroupingByFunction3(){

        System.out.println("testGroupingByFunction3");

        Map<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));

        System.out.println(map.getClass());

        Optional.ofNullable(map).ifPresent(System.out::println);

        menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingDouble(Dish::getCalories)));
    }


    private static void testSummarizingInt(){

        System.out.println("testSummarizingInt");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)));
    }

}
