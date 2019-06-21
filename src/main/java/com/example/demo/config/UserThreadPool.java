package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Created by chenhe
 * @Date 2019-05-30 18:10
 * @Description config  线程池
 */
@Configuration
public class UserThreadPool {

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(10);
    }
}
