package com.example.demo.dao;

import com.alibaba.fastjson.JSON;
import com.example.demo.DemoApplication;
import com.example.demo.config.ConnectionPropertiesDomain;
import com.example.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Created by chenhe
 * @Date 2019-06-04 16:02
 * @Description ConfigurationProperties单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ConfigurationTest {

    @Resource
    private ConnectionPropertiesDomain domain;

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    @Test
    public void test(){

        System.out.println(domain);

    }
}