package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Created by chenhe
 * @Date 2019-05-30 17:32
 * @Description
 *
 * 、@Controller + @ResponseBody = @RestController
 * @RestController //标注是一个Controller类 并且支持json数据格式输出
 */
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${const-dev}")
    private String param;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @GetMapping  get
//    @PostMapping  post
    public String test(HttpServletRequest request, HttpServletResponse response){

        System.out.println(request.getRequestURI());
        System.out.println(request.getServletPath());
        System.out.println(request.getServerName());
        // ConfigurableEnvironment
//        ConfigFileApplicationListener
        log.info("=============");
        return "hello world " + param;
    }

}
