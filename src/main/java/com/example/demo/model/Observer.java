package com.example.demo.model;

import lombok.Data;

/**
 * @Created by chenhe
 * @Date 2019-08-13 16:52
 * @Description TODO
 */
@Data
public abstract class Observer {

    private String name;

    private String info;

    abstract void update(String weather);
}
