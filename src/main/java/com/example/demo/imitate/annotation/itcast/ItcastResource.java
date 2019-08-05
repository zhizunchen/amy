package com.example.demo.imitate.annotation.itcast;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Created by chenhe
 * @Date 2019-08-03 16:14
 * @Description 自定义属性复制注解
 * @see ItcastApplicationContext
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ItcastResource {

    public String name() default "";

}
