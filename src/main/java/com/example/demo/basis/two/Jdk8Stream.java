package com.example.demo.basis.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @Created by chenhe
 * @Date 2019-08-23 13:55
 * @Description jdk8 Stream 流
 */
public class Jdk8Stream {

    public static void main(String[] args) {
//        sort();
//        filter();
//        distinct();
//        limit();
//        skip();
//        map();
//        anyMatch();
//        allMatch();
//        noneMatch();
//        System.out.println(joining());

        List<String> list = new ArrayList<>();
        System.out.println(list.stream().collect(Collectors.joining("-")));

    }
    public static void sort() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .sorted()
//                .sorted(( i1, i2) -> i1.compareTo(i2))
                .forEach(System.out::println);
    }

    public static void distinct() {
        //distinct 过滤相同的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
//                .forEach(item -> System.out.print(item + " "));
                .forEach(System.out::println);
    }

    public static void filter() {
        //filter 根据条件过滤集合数据  return boolean
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 1)
//                .forEach(item -> System.out.print(item + " "));
                .forEach(System.out::println);
    }

    public static void limit() {
        // limit  从头开始筛选出指定的元素个数
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        numbers.stream()
                .limit(3)
                .forEach(System.out::println);
    }

    public static void skip() {
        //skip 返回一个扔掉了前n个元素的流
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        numbers.stream()
                .skip(3)
                .forEach(System.out::println);
    }

    //映射
    public static void map() {
        //map 获取一个新的集合  从某些对象中选择信息
        List<String> words = Arrays.asList("JAVA 8", "Lambdas", "IN", "Action");
        words.stream()
                .map(String::length)
                .forEach(System.out::println);
    }

    //短路操作 && ||
    public static void anyMatch() {
        //anyMatch 流中是否有一个元素能匹配给定的条件  return Boolean 终端操作
        List<String> words = Arrays.asList("JAVA 8", "Lambdas", "IN", "Action");
        if (words.stream().anyMatch(item -> item.length() > 3)) {
            System.out.println("======= anyMatch 方法筛选  =======");
        }
    }

    public static void allMatch() {
        //allMatch 流中的元素是否都能匹配给定的条件   return Boolean 终端操作
        List<String> words = Arrays.asList("JAVA 8", "Lambdas", "IN", "Action");
        if (words.stream().allMatch(item -> item.length() > 3)) {
            System.out.println("======= anyMatch 方法筛选  =======");
        } else {
            System.out.println("=== 不全匹配 ===");
        }
    }

    public static void noneMatch() {
        //noneMatch 流中的没有任何元素与给定的条件匹配   return Boolean 终端操作
        List<String> words = Arrays.asList("JAVA 8", "Lambdas", "IN", "Action");
        if (words.stream().noneMatch(item -> item.length() > 3)) {
            System.out.println("======= anyMatch 方法筛选  =======");
        } else {
            System.out.println("=== 不全匹配 ===");
        }
    }


    //归约  折叠
    public static void reduce() {
        //reduce 初始值0 BinaryOperator将两个元素结合起来产生一个新值
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        numbers.stream()
                .reduce(0, (a, b) -> a + b);

    }

    //汇总
    public static void collect(){
        List<Integer> menu = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        int total = menu.stream().collect(summingInt());
//        menu.stream().collect(averagingInt())
//        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt());
//        IntSummaryStatistics{count=9, sum=4300, min=120,
//                average=477.777778, max=800}
    }

    //连接字符串
    public static String joining(){
        List<String> menu = Arrays.asList("JAVA 8", "Lambdas", "IN", "Action");
        return menu.stream().collect(Collectors.joining("-"));
//        return menu.stream().collect(Collectors.joining());  元素连接无分隔符
    }
}


















