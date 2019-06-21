package com.example.demo.mpgenerator;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.MybatisXMLConfigBuilder;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.demo.config.MybatisPlusConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.FileSystemResource;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class MpGenerator {


    public static void main(String[] args) {

        System.out.println(MpGenerator.class.getClassLoader().getResource("mybatis/mapper/").getFile());


        System.out.println(MpGenerator.class.getResource("/mybatis/mapper/").getFile());

        System.out.println("=====" + Thread.currentThread().getContextClassLoader().getResource("mybatis/mapper/").getFile());

//        MybatisSqlSessionFactoryBuilder builder = new MybatisSqlSessionFactoryBuilder();
//        MybatisXMLConfigBuilder

//        MybatisMapperRegistry
//        SqlSessionFactoryBean

//        ISqlInjector


        // 2019-06-14 mapper的注册与绑定
//        MybatisPlusAutoConfiguration   @Configuration
//        MybatisPlusProperties  @ConfigurationProperties

//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();

//        MybatisPlusAutoConfiguration
//        FileSystemResource
    }

}
