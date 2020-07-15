package com.zhym.java8.chapter2;

import com.zhym.java8.chapter1.Apple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/13 0:08
 */
public class LambdaExpression {

    public static void main(String[] args) {


        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();

        list.sort(byColor);

        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());

        Function<String, Integer> consumer = (s) -> {return s.length();};

        Predicate<Apple> p = a -> a.getColor().equals("Green");

        //Optional.of(new int[]{1, 2, 4, 5, 6, 7}).ifPresent(System.out::println);

        //Stream.of(new int[]{1, 2, 4, 5, 6, 7}).reduce(Math::min).ifPresent(System.out::println);
    }
}
