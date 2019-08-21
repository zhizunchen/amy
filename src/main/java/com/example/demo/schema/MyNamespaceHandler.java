package com.example.demo.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Created by chenhe
 * @Date 2019-08-20 14:17
 * @Description 使用schema模拟xml注入
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
    }
}
