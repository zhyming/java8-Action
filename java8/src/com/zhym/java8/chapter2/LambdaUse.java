package com.zhym.java8.chapter2;

import com.zhym.java8.chapter1.Apple;

import java.util.*;
import java.util.function.*;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/13 0:42
 */
public class LambdaUse {


    private static List<Apple> filter(List<Apple> list, Predicate<Apple> predicate){
        List<Apple> newList = new ArrayList<>();
        for(Apple apple : list){
            if (predicate.test(apple)){
                newList.add(apple);
            }
        }

        return newList;
    }

    private static List<Apple> filter2(List<Apple> list, Predicate<Long> predicate){
        List<Apple> newList = new ArrayList<>();
        for(Apple apple : list){
            if (predicate.test(apple.getWeight())){
                newList.add(apple);
            }
        }

        return newList;
    }

    private static List<Apple> filter3(List<Apple> list, BiPredicate<String, Long> predicate){
        List<Apple> newList = new ArrayList<>();
        for(Apple apple : list){
            if (predicate.test(apple.getColor(), apple.getWeight())){
                newList.add(apple);
            }
        }

        return newList;
    }

    private static void simpleConsumer(List<Apple> list, Consumer<Apple> consumer){
        for (Apple apple: list){

            consumer.accept(apple);
        }
    }

    private static void simple2Consumer(List<Apple> list, BiConsumer<Apple, String> consumer){
        for (Apple apple: list){

            consumer.accept(apple, "XXX");
        }
    }

    private static String simpleFunction(Apple apple, Function<Apple, String> fun){
        return fun.apply(apple);
    }


    public static void main(String[] args) {

        /*Runnable r1 = () -> System.out.println("r1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("r3"));*/

        List<Apple> list = Arrays.asList(new Apple("Green", 100), new Apple("Red", 120));

        List<Apple> newList = filter(list, a -> a.getColor().equals("Green"));
        List<Apple> new2List = filter2(list, w -> w == 120);
        List<Apple> new3List = filter3(list, (s, l) -> s.equals("Red") && l > 100);

/*
        System.out.println(newList);
        System.out.println(new2List);

        System.out.println(new3List);*/

        /*simpleConsumer(list, a -> System.out.println(a));
        simple2Consumer(list, (a, s) -> System.out.println(s+a.getColor() + " : " + a.getWeight()));*/




        Supplier<String> s = String::new;
        System.out.println(s.get().getClass());

    }

    private static void process(Runnable r){
        r.run();
    }

}
