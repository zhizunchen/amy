package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @ComponentScan自动扫描并加载符合条件的组件（@Component @Repository）或者bean定义
 * 最终将这些bean定义加载到ioc容器中   可以通过basePackages等属性来定义粒度
 * 默认spring框架从声明@ComponentScan所在类的package进行扫描
 *
 * 注：springboot的启动类最好放在root package下，默认不指定basePackages
 *
 * */

@SpringBootApplication
@MapperScan("com.example.demo.dao")
//@ImportResource(locations = {"classpath:spring-config-service.xml"})
public class DemoApplication {

    public static void main(String[] args) {
        // 服务启动调用 SpringApplication.run
        // classpath下的application.yml或application.properties配置文件
        SpringApplication.run(DemoApplication.class, args);
    }

}