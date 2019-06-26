package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
@ImportResource(locations = {"classpath:spring-config-service.xml"})
@ComponentScan("com.example.demo") //<context:component-scan>
public class DemoApplication {

    public static void main(String[] args) {
        // 服务启动调用 SpringApplication.run
        // classpath下的application.yml或application.properties配置文件
        SpringApplication.run(DemoApplication.class, args);
    }

}
