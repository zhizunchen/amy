package com.example.demo.basis.two;

import lombok.Data;

/**
 * @Created by chenhe
 * @Date 2019-08-23 16:51
 * @Description Jdk8StreamTest 交易员
 */
@Data
public class Trader {

    private final String name;

    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
