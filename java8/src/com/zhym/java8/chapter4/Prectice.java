package com.zhym.java8.chapter4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/16 23:17
 */
public class Prectice {

    public static void main(String[] args) {
        Trader raoul=new Trader("Raoul","Cambridge");
        Trader mario =new Trader("Mario","Milan");
        Trader alan=new Trader("Alan","Cambridge");
        Trader brian=new Trader("Brian","Cambridge");

        List<Transaction> transactions= Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950));


        //1.Find all transactions in the year 2011 and sort them by value(small to high)
        List<Transaction> result = transactions.stream()
                .filter(t -> 2011 == t.getYear())
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

        System.out.println(result);
        //2.What are all the unique cities where the traders work?
        List<String> result2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result2);



        //3.Find all traders from Cambridge and sort them by name.
        List<Trader> result3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge" .equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println(result3);

        //4.Return a string of allt raders’names sorted alphabetically.
        String result4 = transactions.stream().map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b);

        System.out.println(result4);

        //5.Are any traders based in Milan?
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Milan".equals(t.getCity()))
                .findAny().ifPresent(System.out::println);


        //6.Print all transactions’values from the traders livingin Cambridge.

        List<Integer> result6 = transactions.stream().filter(t -> "Cambridge" .equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(Collectors.toList());

        System.out.println(result6);

        //7.What’s the highest value of all the transactions?

        transactions.stream().reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t1 : t2 ).ifPresent(System.out::println);

        //8.Find the transaction with the smallest value

        transactions.stream().reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1).ifPresent(System.out::println);
    }



}
