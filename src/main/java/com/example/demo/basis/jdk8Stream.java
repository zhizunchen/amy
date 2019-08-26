package com.example.demo.basis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created by chenhe
 * @Date 2019-08-23 13:55
 * @Description jdk8 Stream 流
 */
public class jdk8Stream {

    public static void main(String[] args) {
//        filter();
//        distinct();
//        limit();
//        skip();
        map();
    }

    public static void distinct(){
        //distinct 过滤相同的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
//                .forEach(item -> System.out.print(item + " "));
                .forEach(System.out::println);
    }


    public static void filter(){
        //filter 根据条件过滤集合数据  return oolean
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 1)
//                .forEach(item -> System.out.print(item + " "));
                .forEach(System.out::println);
    }

    public static void limit(){
        // limit  从头开始筛选出指定的元素个数
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        numbers.stream()
                .limit(3)
                .forEach(System.out::println);
    }

    public static void skip(){
        //skip 返回一个扔掉了前n个元素的流
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        numbers.stream()
                .skip(3)
                .forEach(System.out::println);
    }

    //映射
    public static void map(){
        //map 获取一个新的集合  从某些对象中选择信息
        List<String> words = Arrays.asList("JAVA 8", "Lambdas", "IN", "Action");
        words.stream()
                .map(String::length)
                .forEach(System.out::println);
    }




}


















