package com.example.demo.proxy.analogyproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Created by chenhe
 * @Date 2019-08-07 14:49
 * @Description TODO
 */
public class PersonHandler implements InvocationHandler {

    private Object target;

    public PersonHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] agrs) {

        try {
            System.out.println("====start 鼓掌👏");
            Object object = method.invoke(target, agrs);
            System.out.println("====end 鼓掌👏");
            return object;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }
}
