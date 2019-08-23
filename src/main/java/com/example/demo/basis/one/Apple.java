package com.example.demo.basis.one;

import lombok.Data;

/**
 * @Created by chenhe
 * @Date 2019-08-22 10:05
 * @Description TODO
 */
@Data
public class Apple {

    private String color;

    private Integer weight;

    private String address;

    public Apple() {
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }
}
