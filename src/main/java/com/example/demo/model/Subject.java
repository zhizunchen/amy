package com.example.demo.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by chenhe
 * @Date 2019-08-13 16:47
 * @Description
 */
@Slf4j
@Data
public class Subject {

    private List<Observer> list = new ArrayList<Observer>();

    public void attach(Observer server){
        list.add(server);
    }

    public void anotify(){
        String param = "hello world";

        list.forEach(
                item -> item.update(param)
        );
    }

    public static void main(String[] args) {

        Observer observer = new WeathObserver();
        observer.setName("2019-08-13");
        observer.setInfo("天气清凉");


        Subject subject = new Subject();
        subject.attach(observer);

        subject.anotify();

    }
}
