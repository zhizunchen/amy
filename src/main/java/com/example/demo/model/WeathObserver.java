package com.example.demo.model;

/**
 * @Created by chenhe
 * @Date 2019-08-13 17:13
 * @Description
 */
public class WeathObserver extends Observer {

    @Override
    void update(String weather) {

        System.out.println(this.getName() + " " + weather + " " + this.getInfo());
    }
}
