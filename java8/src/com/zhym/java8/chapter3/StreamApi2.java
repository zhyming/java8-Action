package com.zhym.java8.chapter3;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/15 23:15
 */
public class StreamApi2 {

    public static void main(String[] args) {

        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

       /* boolean b = stream.allMatch(a -> a > 5);
        System.out.println(b);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        boolean b2 = stream.anyMatch(a -> a > 5);
        System.out.println(b2);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        boolean b3 = stream.noneMatch(a -> a > 8);
        System.out.println(b3);*/

        /**
         * find
         */

        //stream.findAny().ifPresent(System.out::println);
        //Optional<Integer> opt = stream.findAny();
        //System.out.println(opt.get());
        //stream.findFirst().ifPresent(System.out::println);
       Integer integer = stream.filter(n -> n < 100).findFirst().filter(i -> i == 2).orElse(-1);
//        System.out.println(integer);

        /**
         * reduce
         */

        //stream.reduce((a, b) -> a + b).ifPresent(System.out::println);
        //Integer reduce = stream.reduce(0, (a, b) -> a + b);
        //System.out.println(reduce);
        //Integer sum = stream.reduce(0, Integer::sum);
        //System.out.println(sum);

        //stream.reduce(Integer::min).ifPresent(System.out::println);

        stream.filter(n -> n % 2== 0).reduce((a, b) -> a * b).ifPresent(System.out::println);



    }
}
