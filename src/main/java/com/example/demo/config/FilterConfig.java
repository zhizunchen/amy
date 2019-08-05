package com.example.demo.config;

import com.example.demo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Created by chenhe
 * @Date 2019-08-01 16:51
 * @Description TODO
 */
public class FilterConfig {


    /**
     * 拦截器加载如ioc容器
     * */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        return registration;
    }
}
