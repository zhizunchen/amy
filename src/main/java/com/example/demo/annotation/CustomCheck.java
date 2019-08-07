package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Created by chenhe
 * @Date 2019-06-11 09:04
 * @Description 自定义 注解
 *
 * @see AnnotationObjDemo
 */
//保留的环境
@Retention(RetentionPolicy.RUNTIME)
//注释起作用的位置 field,
// type={Class, interface (including annotation type), or enum declaration}
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD})
public @interface CustomCheck {

// 声名了一个配置参数 返回值类型就是参数的类型（返回值类型只能是基本类型 Class，String，enum）
// 可以通过default 声明参数的默认值
// 如果只有一个参数成员,最好把参数名称设为"value",后加小括号.
// 例:下面的例子FruitName注解就只有一个参数成员。
// String value() default "";

    boolean required() default false;

    String type() default "";

    int digits() default 0;

}
