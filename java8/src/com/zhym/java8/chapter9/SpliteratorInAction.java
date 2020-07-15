package com.zhym.java8.chapter9;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/23 22:07
 */
public class SpliteratorInAction {

    private static String text = "1Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "2Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "3Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "4Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "5Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "6Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "7Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "8Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "9Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow" +
            "\n" +
            "10Metrics and success indications are derived from one of the largest communities in the world – Stack Overflow";

    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(0, 10);
        Spliterator.OfInt spliterator = intStream.spliterator();

        Consumer<Integer> consumer = n -> System.out.println(n);

        spliterator.forEachRemaining(consumer);

        MySpliteratorText mySpliteratorText = new MySpliteratorText(text);
        Optional.ofNullable(mySpliteratorText.stream().count())
                .ifPresent(System.out::println);

       /* Optional.ofNullable(mySpliteratorText.parallelStream().count())
                .ifPresent(System.out::println);*/

        //mySpliteratorText.stream().forEach(System.out::println);
        mySpliteratorText.parallelStream().filter(n ->  !n.equals("")).forEach(System.out::println);

    }

    static class MySpliteratorText{

        private final String[] data;

        public MySpliteratorText(String text) {
            Objects.requireNonNull(text, "The parameter can not be null!");
            this.data = text.split("\n");
        }

        public Stream<String> stream(){
            return StreamSupport.stream(new MySpliterator(), false);
        }

        public Stream<String> parallelStream(){
            return StreamSupport.stream(new MySpliterator(), true);
        }

        private class  MySpliterator implements Spliterator<String>{

            private int start;

            private int end;

            public MySpliterator() {
                this.start = 0;
                this.end = MySpliteratorText.this.data.length - 1;
            }

            public MySpliterator(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public boolean tryAdvance(Consumer<? super String> action) {

                if (start <= end)
                {
                    action.accept(MySpliteratorText.this.data[start ++]);
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<String> trySplit() {

                int mid = (end - start) / 2;
                if (mid <= 1){
                    return null;

                }

                int left = start;
                int right = start + mid;
                start = start + mid + 1;


                return new MySpliterator(left, right);
            }

            @Override
            public long estimateSize() {
                return end - start;
            }

            @Override
            public long getExactSizeIfKnown() {
                return estimateSize();
            }

            @Override
            public int characteristics() {
                return IMMUTABLE|SIZED|SUBSIZED;
            }
        }
    }
}
