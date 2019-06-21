package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Created by chenhe
 * @Date 2019-06-14 16:24
 * @Description ConfigurationProperties测试
 */
@Slf4j
@Configuration
public class ConfigurationPropertiesTest {

    @Bean
//    @ConfigurationProperties(prefix = "connection")
    public ConnectionPropertiesDomain connectionPropertiesDomain(){
        log.info("======初始化配置文件加载类=======");
        return new ConnectionPropertiesDomain();
    }
}
