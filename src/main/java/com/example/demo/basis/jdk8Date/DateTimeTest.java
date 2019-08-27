package com.example.demo.basis.jdk8Date;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * @Created by chenhe
 * @Date 2019-08-27 16:57
 * @Description jdk8 java.time.*
 */
public class DateTimeTest {

    public static void main(String[] args) {


        dateTime();
    }

    //年 月 日 星期几
    public static void localDate(){
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();


        int year1 = date.get(ChronoField.YEAR);
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);
        int day1 = date.get(ChronoField.DAY_OF_MONTH);
    }

    //时分秒 localTime
    public static void localTime(){
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
    }

    public static  void dateTime(){
        //LocalDateTime  LocalDate  LocalTime  日期和时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
    }
}
