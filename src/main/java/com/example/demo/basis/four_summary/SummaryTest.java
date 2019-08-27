package com.example.demo.basis.four_summary;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by chenhe
 * @Date 2019-08-26 17:52
 * @Description jdk8总结
 */
public class SummaryTest {



    /**
     * 匿名类和Lambda表达式中 this和super含义不同
     *
     * */
    public static void testOne(){
        int a = 10;
        Runnable r1 = ()->{
//            int a = 2;
            System.out.println(a);
        };
    }

    public static void testTwo(){
        int a = 10;
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println(a);
            }
        };
    }

    public static void groupBy(){

    }
}


interface Fu{

    void say();

    // JDK1.8中为了加强接口的能力，使得接口可以存在具体的方法，前提是方法需要被default或static关键字所修饰
    //default--让接口拥有具体的方法 让接口内部包含一些默认的方法实现 实现该接口的类  都具有这个默认方法 可以被重写
    default void run(){
        System.out.println("do not stop running !");
    }
    static void show(){
        System.out.println(" this is the show method ");
    }
}
class Zi implements Fu{

    @Override
    public void say() {

    }

    //default修饰的方法可以被重写
//    @Override
//    public void run() {
//
//    }
}
