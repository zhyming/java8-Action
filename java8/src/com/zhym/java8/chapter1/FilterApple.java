package com.zhym.java8.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/6 17:46
 */
public class FilterApple {

    public static List<Apple> getGreenApples(List<Apple> apples){

        List<Apple> list = new ArrayList<>();

        for(Apple apple : apples){
            if ("Green".equals(apple.getColor())){
                list.add(apple);
            }
        }

        return list;
    }

    public static void main(String[] args) {
       /* List<Apple> list = Arrays.asList(new Apple("Yello", 123)
                , new Apple("Green", 213)
                , new Apple("Red", 321));

        List<Apple> result = getGreenApples(list);

        System.out.println(result);*/

       Apple a1 = new Apple("Red", 123);
       Apple a2 = a1;

        System.out.println(a1);
        System.out.println(a2);

        a1.setColor("Blue");
        System.out.println(a1);
        System.out.println(a2);

        a2 = null;
        System.out.println(a1);
        System.out.println(a2);
    }
}
