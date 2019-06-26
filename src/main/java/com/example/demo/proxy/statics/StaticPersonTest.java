package com.example.demo.proxy.statics;

/**
 * @Created by chenhe
 * @Date 2019-06-06 14:14
 * @Description TODO
 */

import com.example.demo.proxy.dynamic.IPerson;

/**
 * 静态代理的弊端
 *
 * 一个代理接口只能服务于一种类型的对象.对于稍大点的项目根本无法胜任.
 * */
public class StaticPersonTest {

    public static void main(String[] args) {
        IPerson proxy = new PersonProxy(new Person());
        proxy.eating();
        proxy.sleep();
    }
}
