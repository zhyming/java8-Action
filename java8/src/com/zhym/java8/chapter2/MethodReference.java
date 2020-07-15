package com.zhym.java8.chapter2;

import com.zhym.java8.chapter1.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/13 23:16
 */
public class MethodReference {

    public static void main(String[] args) {

        /*Consumer<String> consumer = s -> System.out.println(s);

        userConsumer(consumer, "Hello World!");
        System.out.println("==============");
        userConsumer(System.out::println, "Hello World!");

        List<Apple> list = Arrays.asList(new Apple("Green", 100), new Apple("Red", 120), new Apple("Blue", 110));

        List<Apple> list2 = Arrays.asList(new Apple("Green", 100), new Apple("Red", 120), new Apple("Blue", 110));
        /*list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);
        System.out.println("==============");
        list2.sort(Comparator.comparing(Apple::getColor));

        System.out.println(list2);*/

        /*list.stream().forEach(a -> System.out.println(a));

        System.out.println("==============");
        list.stream().forEach(System.out::println);*/

        /*Function<String, Integer> function = Integer::parseInt;

        Integer integer = function.apply("342");

        System.out.println(integer);*/

       /* BiFunction<String, Integer, Character> f = String::charAt;

        Character c = f.apply("BiFunction", 3);

        System.out.println(c);*/

       /*String s = new String("BiFunction");

        Function<Integer, Character> function = s::charAt;

        Character c = function.apply(4);
        System.out.println(c);*/


       /*BiFunction<String, Long, Apple> appleBiFunction = Apple::new;

        Apple redApple = appleBiFunction.apply("Red", 100L);

        System.out.println(redApple);*/

       ThreedFunction<String, String, Long, NApple> function = NApple::new;

       NApple nApple = function.apply("hongfushi", "Red", 130L);

        System.out.println(nApple);

        Comparator.comparing(Apple::getColor);


    }

    private static <T> void userConsumer(Consumer<T> consumer, T t){

        consumer.accept(t);
        consumer.accept(t);

    }

}
