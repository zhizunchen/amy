package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Created by chenhe
 * @Date 2019-06-14 16:24
 * @Description ConfigurationProperties测试
 *
 * javaConfig形式的spring ioc容器
 * 类标注 @Configuration === ioc容器的配置类
 *
 * **************************************************
 * <beans xmlns="http://www.springframework.org/schema/beans"
 *        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
 *        default-lazy-init="true">
 *     <!--bean定义-->
 * </beans>
 *
 *
 *@Configuration
 * public class MockConfiguration{
 *     //bean定义
 * }
 * **************************************************
 *
 */
@Slf4j
@Configuration
public class ConfigurationPropertiesTest {

    /**
     * 任何一个标注了@Bean的方法 其返回值将作为一个bean定义注册
     * 到spring的ioc容器  方法名将默认成该bean定义的id
     *
     *  <bean id="mockService" class="..MockServiceImpl">
     *     ...
     *  </bean>
     * */

    @Bean
//    @ConfigurationProperties(prefix = "connection")
    public ConnectionPropertiesDomain connectionPropertiesDomain(){
        log.info("======初始化配置文件加载类=======");
        return new ConnectionPropertiesDomain();
    }
}
