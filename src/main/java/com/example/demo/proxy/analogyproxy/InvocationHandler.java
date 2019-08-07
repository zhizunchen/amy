package com.example.demo.proxy.analogyproxy;

import java.lang.reflect.Method;

/**
 * @Created by chenhe
 * @Date 2019-08-07 14:43
 * @Description TODO
 */
public interface InvocationHandler {

    Object invoke(Object obj, Method method, Object[] agrs);

}
