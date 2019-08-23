package com.example.demo.basis.one;

/**
 * @Created by chenhe
 * @Date 2019-08-22 10:04
 * @Description TODO
 */
public class AppleWeightPredicate implements ApplePredicate {

    public AppleWeightPredicate() {
        System.out.println("===== apple weight 初始化");
    }

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
