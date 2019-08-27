package com.example.demo.basis.four_summary;

/**
 * @Created by chenhe
 * @Date 2019-08-27 10:32
 * @Description 继承多个相同的方法签名
 */
public class InterfaceImpl {


}
 interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
 interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}

 class C implements B, A {
    public static void main(String... args) {
        new C().hello();
    }
}

interface A1 {
    default void hello() {
        System.out.println("Hello from A");
    }
}
interface B1  {
    default void hello() {
        System.out.println("Hello from B");
    }
}

class C1 implements B1, A1{

    public void hello(){
        B1.super.hello();//显示选择调用接口 B中的方法
    }

    public static void main(String... args) {
        new C().hello();
    }
}