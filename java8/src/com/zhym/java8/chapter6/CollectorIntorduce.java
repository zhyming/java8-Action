package com.zhym.java8.chapter6;

import com.zhym.java8.chapter1.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/18 22:40
 */
public class CollectorIntorduce {

    public static void main(String[] args) {

        List<Apple> list = Arrays.asList(new Apple("Green", 100),
                new Apple("Red", 120),
                new Apple("Blue", 110),
                new Apple("Green", 100),
                new Apple("Red", 120),
                new Apple("Blue", 110));

        List<Apple> blueList = list.stream().filter(a -> a.getColor().equals("Blue")).collect(Collectors.toList());

        //System.out.println(blueList);

        Optional.ofNullable(blueList).ifPresent(System.out::println);

        Map<String, List<Apple>> map = new HashMap<>();

        list.stream().forEach(a -> {
            List<Apple> apples = map.get(a.getColor());
            if (map.get(a.getColor()) == null){
                apples = new ArrayList<>();
                map.put(a.getColor(), apples);
            }
            apples.add(a);
        });

        System.out.println(map);

        Map<String, List<Apple>> map1 = new HashMap<>();
        list.stream().forEach(a -> {
            List<Apple> list1 = Optional.ofNullable(map1.get(a.getColor())).orElseGet(() -> {
                List<Apple> apples = new ArrayList<>();
                map1.put(a.getColor(), apples);
                return apples;
            });

            list1.add(a);

        });

        System.out.println(map1);

        System.out.println(getGroupByCollector(list));
    }

    private static Map<String, List<Apple>> getGroupByCollector(List<Apple> list){
        Map<String, List<Apple>> collect = list.stream().collect(Collectors.groupingBy(Apple::getColor));
        return collect;

    }
}
