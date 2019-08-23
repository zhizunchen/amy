package com.example.demo.basis.one;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by chenhe
 * @Date 2019-08-22 10:08
 * @Description TODO
 */
public class AppleFilter {

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        Apple apple = new Apple("red", 160);
        Apple apple1 = new Apple("green", 140);
        list.add(apple);
        list.add(apple1);

        System.out.println(filter(list, new AppleColorPredicate()));
        System.out.println(filter(list, new AppleWeightPredicate()));


    }


    public static  List<Apple> filter(List<Apple> list, ApplePredicate predicate){

        List<Apple> newList = new ArrayList<>();
        for (Apple apple : list) {
            if(predicate.test(apple)){
                newList.add(apple);
            }
        }
        return newList;
    }
}
