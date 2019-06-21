package com.example.demo.proxy.statics;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Created by chenhe
 * @Date 2019-06-06 11:46
 * @Description TODO
 */
@Slf4j
public class PersonProxy implements IPerson {

    private IPerson iPerson;

    public PersonProxy(IPerson iPerson){
        this.iPerson = iPerson;
    }

    @Override
    public void sleep() {
        log.info("====sleep  start time==" + new Date());
        iPerson.sleep();
        log.info("====sleep  end time==" + new Date());
    }

    @Override
    public void eating() {
        log.info("====eating  start time==" + new Date());
        iPerson.eating();
        log.info("====eating  end time==" + new Date());
    }
}
