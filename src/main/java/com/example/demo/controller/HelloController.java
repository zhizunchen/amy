package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by chenhe
 * @Date 2019-05-30 17:32
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${const-dev}")
    private String param;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(){

        // ConfigurableEnvironment
//        ConfigFileApplicationListener
        log.info("=============");
        return "hello world " + param;
    }

}
