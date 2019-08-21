package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Created by chenhe
 * @Date 2019-08-01 11:22
 * @Description 自定义过滤器注解
 * @see com.example.demo.aspect.MyAspect
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MyAspectAnnotation {
}
