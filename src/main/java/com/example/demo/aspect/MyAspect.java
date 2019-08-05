package com.example.demo.aspect;

import com.example.demo.annotation.CustomCheck;
import com.example.demo.annotation.MyAspectAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Created by chenhe
 * @Date 2019-08-01 10:48
 * @Description 自定义切面
 *
 *
 * @Aspect 申明切面
 *
 * @Pointcut 定义切点 标记方法
 */
@Aspect
@Slf4j
@Component
public class MyAspect {

    @Around(value = "@annotation(myAspectAnnotation)", argNames="joinPoint,myAspectAnnotation")
    public  Object around(ProceedingJoinPoint joinPoint, MyAspectAnnotation myAspectAnnotation) {
        Object obj = null;
        try {
            Signature signature = joinPoint.getSignature();
            log.info("-----------" + signature.getName() + "-----------");

            obj = joinPoint.proceed();
        }catch (Throwable e){}
        return obj;
    }
}
