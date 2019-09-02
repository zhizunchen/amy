package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    //获取cookie
    private void getCookies(HttpServletRequest request, HttpServletResponse response){
        //1
        String name = "name";
        Cookie[] cookies = request.getCookies();
        if (null != cookies){
            for (int i = 0; i< cookies.length; i++){
                Cookie cookie = cookies[i];
                if(name.equals(cookie.getName())){
                    cookie.getValue();
                }
            }
        }
        //2
        List<Cookie> list = new ArrayList(Arrays.asList(cookies));
        list.stream()
                .filter(item->"name".equals(item.getName()))
                .map(item->item.getValue())
                .collect(Collectors.toList());
    }
    //设置cookie
    public void setCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie c = new Cookie("name", "value");
        c.setMaxAge(60*60*24);
        c.setDomain("chenhe.com");
        c.setPath("/");
        response.addCookie(c);
    }

}
