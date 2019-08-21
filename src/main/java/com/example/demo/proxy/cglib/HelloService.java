package com.example.demo.proxy.cglib;

/**
 * @Created by chenhe
 * @Date 2019-08-21 10:03
 * @Description cglib 业务类 不实现任何接口
 */
public class HelloService {

    public HelloService() {
        System.out.println("HelloService构造");
    }

    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     */
    final public String sayOthers(String name) {
        System.out.println("HelloService:sayOthers>>"+name);
        return null;
    }

    public void sayHello() {
        System.out.println("HelloService:sayHello");
    }

}
