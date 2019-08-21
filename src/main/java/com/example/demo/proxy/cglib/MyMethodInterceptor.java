package com.example.demo.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * @Created by chenhe
 * @Date 2019-08-21 10:11
 * @Description 自定义MethodInterceptor
 */
public class MyMethodInterceptor implements MethodInterceptor {

    /**
     * sub：cglib生成的代理对象
     * method：被代理对象方法
     * objects：方法入参
     * methodProxy: 代理方法
     */
    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");
        Object object = methodProxy.invokeSuper(sub, objects);
        System.out.println("======插入后者通知======");
        return object;
    }

    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/lsjr3/chenhe");

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(HelloService.class);

        enhancer.setCallback(new MyMethodInterceptor());

        HelloService proxy = (HelloService) enhancer.create();

        proxy.sayHello();

        System.out.println(proxy.getClass().getName());

    }
}


//package com.lanhuigu.spring.proxy.cglib;
//
//        import java.lang.reflect.Method;
//        import net.sf.cglib.core.ReflectUtils;
//        import net.sf.cglib.core.Signature;
//        import net.sf.cglib.proxy.*;
//
//public class HelloService$$EnhancerByCGLIB$$4da4ebaf extends HelloService
//        implements Factory{
//    private boolean CGLIB$BOUND;
//    public static Object CGLIB$FACTORY_DATA;
//    private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
//    private static final Callback CGLIB$STATIC_CALLBACKS[];
//    private MethodInterceptor CGLIB$CALLBACK_0; // 拦截器
//    private static Object CGLIB$CALLBACK_FILTER;
//    private static final Method CGLIB$sayHello$0$Method; // 被代理方法
//    private static final MethodProxy CGLIB$sayHello$0$Proxy; // 代理方法
//    private static final Object CGLIB$emptyArgs[];
//    private static final Method CGLIB$equals$1$Method;
//    private static final MethodProxy CGLIB$equals$1$Proxy;
//    private static final Method CGLIB$toString$2$Method;
//    private static final MethodProxy CGLIB$toString$2$Proxy;
//    private static final Method CGLIB$hashCode$3$Method;
//    private static final MethodProxy CGLIB$hashCode$3$Proxy;
//    private static final Method CGLIB$clone$4$Method;
//    private static final MethodProxy CGLIB$clone$4$Proxy;
//
//    static void CGLIB$STATICHOOK1()
//    {
//        Method amethod[];
//        Method amethod1[];
//        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
//        CGLIB$emptyArgs = new Object[0];
//        // 代理类
//        Class class1 = Class.forName("com.lanhuigu.spring.proxy.cglib.HelloService$$EnhancerByCGLIB$$4da4ebaf");
//        // 被代理类
//        Class class2;
//        amethod = ReflectUtils.findMethods(new String[] {
//                "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"
//        }, (class2 = Class.forName("java.lang.Object")).getDeclaredMethods());
//        Method[]  = amethod;
//        CGLIB$equals$1$Method = amethod[0];
//        CGLIB$equals$1$Proxy = MethodProxy.create(class2, class1, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");
//        CGLIB$toString$2$Method = amethod[1];
//        CGLIB$toString$2$Proxy = MethodProxy.create(class2, class1, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");
//        CGLIB$hashCode$3$Method = amethod[2];
//        CGLIB$hashCode$3$Proxy = MethodProxy.create(class2, class1, "()I", "hashCode", "CGLIB$hashCode$3");
//        CGLIB$clone$4$Method = amethod[3];
//        CGLIB$clone$4$Proxy = MethodProxy.create(class2, class1, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");
//        amethod1 = ReflectUtils.findMethods(new String[] {
//                "sayHello", "()V"
//        }, (class2 = Class.forName("com.lanhuigu.spring.proxy.cglib.HelloService")).getDeclaredMethods());
//        Method[] 1 = amethod1;
//        CGLIB$sayHello$0$Method = amethod1[0];
//        CGLIB$sayHello$0$Proxy = MethodProxy.create(class2, class1, "()V", "sayHello", "CGLIB$sayHello$0");
//    }
//
//    final void CGLIB$sayHello$0()
//    {
//        super.sayHello();
//    }
//
//    public final void sayHello()
//    {
//        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
//        if(this.CGLIB$CALLBACK_0 == null) {
//            CGLIB$BIND_CALLBACKS(this);
//            var10000 = this.CGLIB$CALLBACK_0;
//        }
//
//        if(var10000 != null) {
//            // 调用拦截器
//            var10000.intercept(this, CGLIB$setPerson$0$Method, CGLIB$emptyArgs, CGLIB$setPerson$0$Proxy);
//        } else {
//            super.sayHello();
//        }
//    }
//	......
//            ......
//}
