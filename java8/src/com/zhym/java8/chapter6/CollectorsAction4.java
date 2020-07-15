package com.zhym.java8.chapter6;


import com.zhym.java8.chapter3.Dish;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.zhym.java8.chapter6.CollectorsAction.menu;
/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/20 0:25
 */
public class CollectorsAction4 {

    public static void main(String[] args) {

        testSummarizingDouble();
        testSummingDouble();
        testSummingLong();
        testSummingInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMap2();
        testToConcurrentMap3();
        testToList();
        testToSet();

        testToMap();
        testToMap2();
        testToMap3();
    }

    private static void testSummarizingDouble(){
        System.out.println("testSummarizingDouble");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);


        menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    }

    private static void testSummingDouble(){
        System.out.println("testSummingDouble");

        Optional.ofNullable(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().mapToInt(Dish::getCalories).sum()).ifPresent(System.out::println);

        menu.stream().collect(Collectors.summingDouble(Dish::getCalories));

    }

    private static void testSummingLong(){
        System.out.println("testSummingLong");

        Optional.ofNullable(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
                .ifPresent(System.out::println);

        //Optional.ofNullable(menu.stream().mapToInt(Dish::getCalories).sum()).ifPresent(System.out::println);

    }
    private static void testSummingInt(){
        System.out.println("testSummingInt");

        Optional.ofNullable(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

        //Optional.ofNullable(menu.stream().mapToInt(Dish::getCalories).sum()).ifPresent(System.out::println);

    }

    private static void testToCollection(){
        System.out.println("testToCollection");

        Optional.ofNullable(menu.stream().filter(d -> d.getCalories() > 600).collect(Collectors.toCollection(LinkedList::new)))
                .ifPresent(System.out::println);

        menu.stream().filter(n -> n.getCalories() > 600).collect(Collectors.toCollection(LinkedList::new));
    }

    private static void testToConcurrentMap(){
        System.out.println("testToConcurrentMap");

        Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });


        menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getType));
    }

    private static void testToConcurrentMap2(){
        System.out.println("testToConcurrentMap2");

        Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, d -> 1, Integer::sum)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

        menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, d -> 1, Integer::sum));
    }

    private static void testToConcurrentMap3(){
        System.out.println("testToConcurrentMap4");

        Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, d -> 1, Integer::sum, ConcurrentSkipListMap::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

        menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, d -> 1, Integer::sum, ConcurrentSkipListMap::new));
    }
    private static void testToList(){
        System.out.println("testToList");

        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.toList()))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

        menu.stream().map(Dish::getName).collect(Collectors.toList());
    }
    private static void testToSet(){
            System.out.println("testToSet");


            Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.toSet()))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

            menu.stream().map(Dish::getName).collect(Collectors.toSet());
    }

    private static void testToMap(){
        System.out.println("testToMap");

        Optional.ofNullable(menu.stream().collect(Collectors.toMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    private static void testToMap2(){
        System.out.println("testToMap2");

        Optional.ofNullable(menu.stream().collect(Collectors.toMap(Dish::getType, d -> 1, Integer::sum)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    private static void testToMap3(){
        System.out.println("testToMap3");

        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(
                Collectors.toMap(Dish::getType, d -> 1, Integer::sum),
                Collections::synchronizedMap
        ))).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });

        System.out.println("===============================");
        Optional.ofNullable(menu.stream().collect(Collectors.toMap(Dish::getType, d -> 1, Integer::sum, Hashtable::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }


}
