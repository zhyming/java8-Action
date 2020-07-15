package com.zhym.java8.chapter3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/14 15:00
 */
public class SimpleStream {

    public static void main(String[] args) {
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

        //List<String> list = getDishNameByCalories(menu);
        //List<String> list = getDishNmaeByStream(menu);
        /*Stream<Dish> stream = menu.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);*/

        List<String> list = menu.stream().filter(dish -> {
            System.out.println("begin--->filter");
            return dish.getCalories() > 300;
        }).map(dish -> {
            System.out.println("begin--->map");
            return dish.getName();
        }).limit(3).collect(Collectors.toList());
        System.out.println("=================");
        System.out.println(list);

        //System.out.println(list);
    }

    private static List<String> getDishNmaeByStream(List<Dish> menu){

       return menu.stream().filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .sorted(Comparator.comparing(Dish::getName))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }


    private static List<String> getDishNameByCalories(List<Dish> menu){

        List<Dish> list = new  ArrayList<>();
        for (Dish dish : menu){

            if (dish.getCalories() < 400){
                list.add(dish);
            }
        }

        list.sort(Comparator.comparingInt(Dish::getCalories));

        Collections.reverse(list);
        List<String> result = new ArrayList<>();
        for (Dish dish : list){
            result.add(dish.getName());
        }



        return result;
    }

}
