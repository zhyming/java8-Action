package com.zhym.java8.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/14 16:34
 */
public class CreateStream {


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

        //menu.stream().forEach(System.out::println);

        /*Stream.of( new Dish("frenchfries",true,530,Dish.Type.OTHER),
                new Dish("rice",true,350,Dish.Type.OTHER)).forEach(System.out::println);*/

        /*Arrays.stream(new Dish[]{ new Dish("pizza",true,550,Dish.Type.OTHER),
                new Dish("prawns",false,300,Dish.Type.FISH)}).forEach(System.out::println);*/

        //createStreamByFile();
        //createStreamByFile().forEach(System.out::println);

        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        //System.out.println(Math.random());

        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        Stream.generate(new ObjSupplier()).limit(10).forEach(System.out::println);
    }

    private static Stream<String> createStreamByFile(){

        Path path = Paths.get("E:\\Code\\MIB\\mib-fjnx\\ThreadStudy\\java8\\src\\com\\zhym\\java8\\chapter3\\CreateStream.java");
        try (Stream<String> lines = Files.lines(path)){
            lines.forEach(System.out::println);
            return lines;
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    static class ObjSupplier implements Supplier<Obj>{

        private int index = 0;

        private Random random = new Random(System.currentTimeMillis());
        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index, "Name-->" + index);
        }
    }

    static class Obj{
        private int id;

        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
