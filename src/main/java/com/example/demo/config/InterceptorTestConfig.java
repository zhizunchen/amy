package com.example.demo.config;

import com.example.demo.interceptor.MybatisTestInterceptor;
import com.example.demo.interceptor.PrepareInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Created by chenhe
 * @Date 2019-06-05 10:40
 * @Description 自定义拦截器测试
 */
@Slf4j
@Configuration
public class InterceptorTestConfig {

//  模拟乐观锁 version
//    @Bean
//    public MybatisTestInterceptor mybatisTestInterceptor(){
//        log.info("初始化自定义拦截器");
//        return new MybatisTestInterceptor();
//    }

    /**
     * 更新操作 update_time
     * 新增操作 create_time
     * */
    @Bean
    public PrepareInterceptor prepareInterceptor(){
        log.info("初始化前置拦截器");
        return new PrepareInterceptor();
    }
}
