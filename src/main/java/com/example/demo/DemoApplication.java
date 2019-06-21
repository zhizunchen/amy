package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描Mapper文件夹
@MapperScan("com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        // 服务启动调用 SpringApplication.run
        // classpath下的application.yml或application.properties配置文件
        SpringApplication.run(DemoApplication.class, args);
    }

}
