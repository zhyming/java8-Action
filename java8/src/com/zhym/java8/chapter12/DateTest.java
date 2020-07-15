package com.zhym.java8.chapter12;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/28 0:39
 */
public class DateTest {

    public static void main(String[] args) throws ParseException, InterruptedException {

       /* Date date = new Date(116, 2, 18);
        System.out.println(date);*/

       /*for (int i = 0; i < 30; i ++){
           new Thread(i + ""){
               @Override
               public void run() {
                   for (int x = 0; x < 100; x ++){
                       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                       Date parseDate = null;
                       try {
                           parseDate = format.parse("2016-05-05");
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }
                       System.out.println(parseDate);
                   }
               }
           }.start();
       }*/


       //testLocalDate();
        //testLocalTime();
        //combineLocalDateAndTime();
        //testInstant();
        //testDuration();
        //testPeriod();
        //testDateFormat();
        //testDateParse();


        Object a = new Object();
        a = "a";
        Object b = a;

        a = "----";

        System.out.println(a);
        System.out.println(b);



    }

    private static void testLocalDate(){

        LocalDate localDate = LocalDate.of(116, 11, 13);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());


    }

    private static void testLocalTime(){

        LocalTime time = LocalTime.now();

        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }


    private static void combineLocalDateAndTime(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime.toString());
        System.out.println(LocalDateTime.now().toString());

    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());


    }

    private static void testDuration(){

        LocalDateTime start = LocalDateTime.now();

        LocalDateTime beforDateTime = start.minusHours(1);

        Duration duration = Duration.between(start, beforDateTime);

        System.out.println(duration.toHours());
    }

    private static void testPeriod(){

        Period period = Period.between(LocalDate.of(2011, 1, 3), LocalDate.of(2014, 2, 21));

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    private static void testDateFormat(){


        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        String format = localDate.format(DateTimeFormatter.ISO_DATE);
        System.out.println(format);


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String format1 = localDate.format(dateTimeFormatter);
        System.out.println(format1);
    }



    private static void testDateParse(){

        String date = "20161113";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println(localDate);


    }
}
