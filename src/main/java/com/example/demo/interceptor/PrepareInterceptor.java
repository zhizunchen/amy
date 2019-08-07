package com.example.demo.interceptor;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @Created by chenhe
 * @Date 2019-06-05 17:08
 * @Description 前置拦截器
 *
 * type=>mybatis 的四大对象:
 *                         ParameterHandler,
 *                         ResultSetHandler,
 *                         StatementHandler,
 *                         Executor.
 */

//该类注册成为拦截器
@Intercepts({
        //要拦截哪些类 哪些方法
        @Signature(
                type = Executor.class, //ParameterHandler,ResultSetHandler,StatementHandler,Executor
                method = "update", //就是sqlsession的新增，删除，修改操作  所有执行executor的update方法都会被该拦截器拦截到。
                args = {MappedStatement.class, Object.class}
        )
})
public class PrepareInterceptor implements Interceptor {

    private static final String NAME_ENTITY = Constants.ENTITY;


    public void test(){
    //        XMLConfigBuilder
    // XMLConfigBuilder是对mybatis的配置文件进行解析的类，
    // 会对myabtis解析后的信息存放在Configuration对象中
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //注解中method的值
        String methodName = invocation.getMethod().getName();
        //SQL类型
        SqlCommandType commandType = mappedStatement.getSqlCommandType();
        if("update".equals(methodName)){
            Object param = invocation.getArgs()[1];
            //entity = et 实体类
            Object et = null;
            if(param instanceof Map){
                Map map = (Map)param;
                if(map.containsKey(NAME_ENTITY)){
                    et = map.get(NAME_ENTITY);
                }
            }else if(param instanceof Object){
                et = param;
            }

            Date currentDate = new Date(System.currentTimeMillis());

            if(SqlCommandType.INSERT.equals(commandType)){
                Field field = et.getClass().getDeclaredField("createTime");
                field.setAccessible(true);
                field.set(et, currentDate);
            }else if (SqlCommandType.UPDATE.equals(commandType)){
                Field field = et.getClass().getDeclaredField("updateTime");
                field.setAccessible(true);
                field.set(et, currentDate);
            }

        }
        return invocation.proceed();
    }

    // 主要是为了把这个拦截器生成一个代理放到拦截器链中
    //定义的插件注册到插件链
    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public static void main(String[] args) {
        PrepareInterceptor interceptor = new PrepareInterceptor();
        Intercepts annotation = interceptor.getClass().getAnnotation(Intercepts.class);
        Signature[] sigs = annotation.value();
        for (Signature signature: sigs) {
            System.out.println(signature.type() + "==" + signature.method() + "==" + signature.args()[0] + "===" + signature.args()[1]);
        }
    }

}
