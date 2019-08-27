package com.example.demo.basis.one;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Created by chenhe
 * @Date 2019-08-22 10:32
 * @Description lambda 行为参数化
 */
public class AppleFilterLambda {

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        Apple apple1 = new Apple("red", 160);
        Apple apple2 = new Apple("green", 140);
        list.add(apple1);
        list.add(apple2);

//        newList.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight().compareTo(o2.getWeight());
//            }
//        });
//        newList.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())


//        list.stream()
//                .filter(d->d.getWeight()<100)
//                .sorted(Comparator.comparing(Apple::getWeight))
//                .map(Apple::getColor)
//                .collect(Collectors.toList());


        List newList = list.stream()
                .filter(apple -> "green".equals(apple.getColor()))
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(newList);

    }

//    List<Apple> newList = filter(list, (Apple apple) -> "red".equals(apple.getColor()));
    public static  List<Apple> filter(List<Apple> list, ApplePredicate predicate){

        List<Apple> newList = new ArrayList<>();
        for (Apple apple : list) {
            if(predicate.test(apple)){
                newList.add(apple);
            }
        }
        return newList;
    }

//    test(()->{
//        System.out.println("线程start");
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("线程end");
//    });
    public static void test(Runnable runnable){
        runnable.run();
    }

    // java.util.function.Predicate
//    boolean test(T t);
//    testFunction((Apple app) -> "green".equals(app.getColor()), apple2);
    public static void testFunction(Predicate<Apple> p, Apple a){
        if(p.test(a)){
            System.out.println("==========");
        }
    }
}
