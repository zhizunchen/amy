package com.example.demo.basis.one;

/**
 * @Created by chenhe
 * @Date 2019-08-22 10:06
 * @Description TODO
 */
public class AppleColorPredicate implements ApplePredicate {

    public AppleColorPredicate() {
        System.out.println("=====Apple color 初始化");
    }

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor().toLowerCase());
    }
}
