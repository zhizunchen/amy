package com.example.demo.config;

import com.example.demo.autoconfigure.MybatisPlusAutoConfigureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Created by chenhe
 * @Date 2019-06-24 15:18
 * @Description  ConditionalOnMissingBean 测试
 */

@Slf4j
@Configuration
public class MybatisPlusAutoConfigurationDemo {

    @Bean
    @ConditionalOnMissingBean(MybatisPlusAutoConfigureUtil.class)
    public MybatisPlusAutoConfigureUtil mybatisPlusAutoConfigureUtil(){
        MybatisPlusAutoConfigureUtil util = new MybatisPlusAutoConfigureUtil();
        util.setAge(12);
        util.setName("12345678");
        log.info("容器内未存在实体时创建！");
        return util;
    }

}
