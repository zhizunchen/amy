package com.example.demo.proxy.analogyproxy;


import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Created by chenhe
 * @Date 2019-08-07 15:03
 * @Description 模拟proxy newProxyInstance方法
 */
public class Proxy {

    public static Object newProxyInstance(ClassLoader loader, Class cls, InvocationHandler handler) throws Exception{
        String rt = "\r\t";
        String space = " ";
        String methodStr = "";

        //文件拼接
        for (Method method : cls.getMethods()){
            String paraStr = "";
            String paraObj = "";
            String paramStr = "";
            methodStr += " public " + method.getReturnType().getSimpleName() + space + method.getName() + space + "(" ;
            Class<?>[] clsarr = method.getParameterTypes();
            for (int i = 0; i < clsarr.length; i++) {
                Class paramClass = clsarr[i];
                paramStr += "," + paramClass.getSimpleName() + " args" + i ;
                paraStr += ",args" + i ;
                paraObj += paramClass.getSimpleName() + ".class";
            }

            paraStr = paraStr.isEmpty()? paraStr : paraStr.substring(1);
            paramStr = paramStr.isEmpty()? paramStr : paramStr.substring(1);

            methodStr = methodStr + paramStr + ") {" + rt +
                    "  try{" + rt +
                    "   Method md = " + cls.getSimpleName() + ".class.getMethod(\"" + method.getName() + "\",new Class[]{" +paraObj + "});" + rt +
                    "   return ("+ method.getReturnType().getSimpleName()+ ") handler.invoke(this," +
                    " md , new Object[]{" +paraStr.trim() + "});" + rt +
                    "  }catch(Exception e){ e.printStackTrace();}" + rt +
                    "  return null;" +
                    " }" + rt;
        }
        String str = "package com.example.demo.proxy.analogyproxy;" + rt +
                     "import java.lang.reflect.Method;" + rt +
                     "public class $Proxy0 implements " + cls.getSimpleName() + "{" + rt +
                     " private InvocationHandler handler;" + rt +
                     " public $Proxy0(InvocationHandler handler){" + rt +
                     "  this.handler = handler;" + rt +
                     " }" + rt +
                      methodStr + "}";

        //产生代理类的java文件
        String filename = System.getProperty("user.dir") +"/target/classes/com/example/demo/proxy/analogyproxy/$Proxy0.java";
        File file = new File(filename);
        FileUtils.writeStringToFile(file, str);

        //编译
        //拿到编译器
        JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
        //文件管理者
        StandardJavaFileManager fileMgr = complier.getStandardFileManager(null, null, null);
        //获取文件
        Iterable units = fileMgr.getJavaFileObjects(filename);
        //编译任务
        JavaCompiler.CompilationTask t = complier.getTask(null, fileMgr, null, null, null, units);
        //进行编译
        t.call();
        fileMgr.close();


        //load 到内存
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("com.example.demo.proxy.analogyproxy.$Proxy0");

        Constructor ctr = c.getConstructor(InvocationHandler.class);
        return ctr.newInstance(handler);
    }

    public static void main(String[] args) throws Exception {
        Person person = new PersonImpl();
        InvocationHandler handler = new PersonHandler(person) ;

        Person person1 = (Person) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                                                         Person.class,
                                                         handler);
        System.out.println(person1.getClass());
        System.out.println(person1.say(" 7.7"));

    }

}

