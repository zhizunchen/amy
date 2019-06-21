package com.example.demo.proxy.dynamic;

/**
 * @Created by chenhe
 * @Date 2019-06-06 11:45
 * @Description TODO
 */
public class Person implements IPerson {
    @Override
    public void sleep() {
        System.out.println("==sleep==");
    }

    @Override
    public void eating() {
        System.out.println("====eating===");
    }
}
