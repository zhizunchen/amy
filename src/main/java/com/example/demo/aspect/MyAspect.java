package com.example.demo.aspect;

import com.example.demo.annotation.CustomCheck;
import com.example.demo.annotation.MyAspectAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Created by chenhe
 * @Date 2019-08-01 10:48
 * @Description 自定义切面  注解匹配
 *
 * @Aspect 申明切面
 *
 * @Pointcut 定义切点 标记方法
 *
 * @annotation 方法级别
 *
 * @within 类级别
 *
 * @target 类级别
 *
 * @args 参数级别
 *
 *
 *
 */
@Aspect
@Slf4j
@Component
public class MyAspect {

    //MethodInterceptor
    @Around(value = "@annotation(myAspectAnnotation)", argNames="joinPoint,myAspectAnnotation")
    public  Object around(ProceedingJoinPoint joinPoint, MyAspectAnnotation myAspectAnnotation) {
        Object obj = null;
        try {
            Object [] params = joinPoint.getArgs();
            for ( Object  param : params) {
                System.out.println(param.toString());
            }


            Signature signature = joinPoint.getSignature();
            log.info("-----------" + signature.getName() + "-----------");

            obj = joinPoint.proceed();
        }catch (Throwable e){}
        return obj;
    }
}
