package com.example.demo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.builder.xml.XMLStatementBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Created by chenhe
 * @Date 2019-06-04 16:56
 * @Description mybatis-plus plus相关， 拦截器功能
 */

@Slf4j
@Configuration
public class MybatisPlusConfig {

    /**
     * 乐观锁 插件
     * 代理模式
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLoker() {
        log.info("初始化Mybatis乐观锁插件");
        return new OptimisticLockerInterceptor();
    }

    /**
     * 逻辑删除
     * sql注入
     * */
    @Bean
    public ISqlInjector sqlInjector(){
//        new SqlSessionFactoryBean
//        XMLMapperBuilder
//        XMLStatementBuilder
//        MapperBuilderAssistant
//        MapperRegistry
//        MapperAnnotationBuilder

        log.info("初始化Mybatis逻辑删除");
        return new LogicSqlInjector();
    }

    /**
     * 性能分析插件
     * SQL执行效率插件
     *
     * 该插件只用于开发环境， 不建议生产环境使用
     */
    @Bean
    @Profile({"dev","test"}) //设置dev test环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        interceptor.setFormat(true);  //SQL是否格式化， 默认为false
        interceptor.setMaxTime(100);  // sql执行最大时长， 超过自动停止运行，
        log.info("初始化SQL执行效率插件");
        return interceptor;
    }

    /**
     * 分页插件
     * */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        log.info("初始化分页插件");
        return new PaginationInterceptor();
    }
}
