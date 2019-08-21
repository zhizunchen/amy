package com.example.demo.proxy.dynamic;

import javax.security.auth.Subject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Created by chenhe
 * @Date 2019-06-06 11:58
 * @Description JDk动态代理
 *
 * JDK动态代理
 * 在JDK1.3之后加入了可协助开发的动态代理功能.不必为特定对象与方法编
 * 写特定的代理对象,使用动态代理,可以使得一个处理者(Handler)服务于各个对象
 *
 *  一个处理者的类设计必须实现java.lang.reflect.InvocationHandler接口.
 *
 *  通过InvocationHandler接口实现的动态代理只能代理接口的实现类.
 */

public class PersonTest {

    public static void main(String[] args) {
        IPerson person1 = new Person();
        InvocationHandler handler = new DynaProxyHandler(person1);

        //Proxy的newProxyInstance方法来创建代理对象，
        //
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneraedFiles", "true");

        IPerson person = (IPerson) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                                                          person1.getClass().getInterfaces(),
                                                          handler);

        person.eating();
        person.sleep();
    }

}

class DynaProxyHandler implements InvocationHandler{

    private Object target;

    public DynaProxyHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        System.out.println("执行 method" + method.getName() + "消耗时间=========" + (System.currentTimeMillis() - startTime));
        return result;
    }

}


//final class $Proxy0 extends Proxy implements Action {
//    private static Method m1;
//    private static Method m3;
//    private static Method m2;
//    private static Method m0;
//
//    public $Proxy0(InvocationHandler var1) throws  {
//        super(var1);
//    }
//
//    public final void doSomething() throws  {
//        try {
//            super.h.invoke(this, m3, (Object[])null);
//        } catch (RuntimeException | Error var2) {
//            throw var2;
//        } catch (Throwable var3) {
//            throw new UndeclaredThrowableException(var3);
//        }
//    }
//
//    static {
//        try {
//            m3 = Class.forName("Action").getMethod("doSomething", new Class[0]);
//        } catch (NoSuchMethodException var2) {
//            throw new NoSuchMethodError(var2.getMessage());
//        } catch (ClassNotFoundException var3) {
//            throw new NoClassDefFoundError(var3.getMessage());
//        }
//    }
//}

