package com.zhym.java8.chapter3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/16 22:28
 */
public class NumericStream {

    public static void main(String[] args) {

        //Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7);

        //stream.filter(i -> i > 3).reduce(Integer::sum).ifPresent(System.out::println);
        //IntStream intStream = stream.mapToInt(Integer::intValue);

        //intStream.filter(i -> i > 3).sum();


        int a = 8;

        /*List<Integer> lit = Stream.iterate(1, n -> n + 1)
                .limit(100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .collect(Collectors.toList());
        System.out.println(lit);*/

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b ) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a="+ r[0] + " b=" + r[1] +" c="+r[2]));

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b ) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a="+ r[0] + " b=" + r[1] +" c="+r[2]));
    }
}
