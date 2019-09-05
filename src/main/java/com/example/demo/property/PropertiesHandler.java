package com.example.demo.property;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Created by chenhe
 * @Date 2019-09-04 17:41
 * @Description 模拟PropertyPlaceholderConfigurer 属性占位符
 * Bean定义全部加载完毕后且Bean实例化之前通过postProcessBeanFactory方法一次性地替换了占位符"${}"
 */
@Service
public class PropertiesHandler implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        Properties mergedProps = this.mergeProperties();


        processProperties(beanFactory, mergedProps);


    }
    public Properties mergeProperties(){

        String path = "target/classes/test.properties";

        Properties result = new Properties();
        try {
            result.load(new InputStreamReader(new BufferedInputStream(new FileInputStream(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    public void processProperties(ConfigurableListableBeanFactory beanFactory, Properties mergedProps){
        String[] beanNames = beanFactory.getBeanDefinitionNames();//获取所有bean Name
        for (String curName :beanNames) {

            BeanDefinition bd = beanFactory.getBeanDefinition(curName);
            System.out.println(curName + " bean properties: " + bd.getPropertyValues().getPropertyValues().length);

//            this.visitPropertyValues(bd.getPropertyValues(), mergedProps);
        }
    }

    public void visitPropertyValues(MutablePropertyValues pvs, Properties mergedProps){
        PropertyValue[] pvArray = pvs.getPropertyValues();
        for (PropertyValue pv:pvArray){
            Object value = pv.getValue();
            if (value instanceof TypedStringValue) {
                TypedStringValue typedStringValue = (TypedStringValue) value;
                String stringValue = typedStringValue.getValue();
                if (stringValue != null) {
                    System.out.println("========11111111111111111=======" + stringValue);
                }
            }
        }

//        AbstractAutowireCapableBeanFactory
    }
}





















