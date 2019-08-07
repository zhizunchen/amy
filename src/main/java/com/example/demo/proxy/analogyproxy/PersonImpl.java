package com.example.demo.proxy.analogyproxy;

/**
 * @Created by chenhe
 * @Date 2019-08-07 14:51
 * @Description TODO
 */
public class PersonImpl implements Person {

    @Override
    public String say(String info) {
        return "person say" + info;
    }
}
