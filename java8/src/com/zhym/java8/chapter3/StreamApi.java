package com.zhym.java8.chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/15 22:29
 */
public class StreamApi {
    public static void main(String[] args) {

       /* List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 7, 8, 1);

        List<Integer> result = list.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());


        //System.out.println(result);

        List<Integer> result2 = list.stream().distinct().collect(Collectors.toList());

        //System.out.println(result2);

        List<Integer> result3 = list.stream().skip(5).collect(Collectors.toList());
        //System.out.println(result3);

        List<Integer> result4 = list.stream().limit(5).collect(Collectors.toList());
        //System.out.println(result4);

        */
        /*
        map api
         */

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 7, 8, 1);

        //List<Integer> result = list.stream().map(m -> m * 2).collect(Collectors.toList());

        //System.out.println(result);

        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",false,700,Dish.Type.MEAT),
                new Dish("chicken",false,400,Dish.Type.MEAT),
                new Dish("frenchfries",true,530,Dish.Type.OTHER),
                new Dish("rice",true,350,Dish.Type.OTHER),
                new Dish("seasonfruit",true,120,Dish.Type.OTHER),
                new Dish("pizza",true,550,Dish.Type.OTHER),
                new Dish("prawns",false,300,Dish.Type.FISH),
                new Dish("salmon",false,450,Dish.Type.FISH));

        List<String> result = menu.stream().map(Dish::getName).collect(Collectors.toList());
        //System.out.println(result);

        String[] arr = new String[]{"frenchfries", "seasonfruit"};

        Arrays.stream(arr).map(m -> m.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

    }
}
