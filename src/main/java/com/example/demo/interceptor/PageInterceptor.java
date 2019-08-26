//package com.example.demo.interceptor;
//
//import org.apache.ibatis.executor.parameter.ParameterHandler;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.DefaultReflectorFactory;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @Author chenhe
// * @Date 2019/7/10 15:55
// * @Description 分页拦截器测试
// */
//@Intercepts(@Signature(
//        type= StatementHandler.class,
//        method = "prepare",
//        args = {Connection.class, Integer.class}))
//public class PageInterceptor implements Interceptor {
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        StatementHandler handler = (StatementHandler)invocation.getTarget();
//        MetaObject metaObject = MetaObject.forObject(handler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
//                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
//        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
//        //配置文件中方法的id
//        String id = mappedStatement.getId();
//        //拦截分页查询的方法 采用正则表达式匹配
//        if(id.matches(".+ByPage$")){
//            BoundSql boundSql = handler.getBoundSql();
//            //原始sql
//            String sql =  boundSql.getSql();
//            //查询总条数的sql语句
//            String countSql = "select count(*) from (" + sql +")";
//            Connection conn = (Connection)invocation.getArgs()[0];
//            PreparedStatement ps = conn.prepareStatement(countSql);
//
//            //count参数拼装
//            ParameterHandler  parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
//            parameterHandler.setParameters(ps);//参数组装
//            ResultSet rs = ps.executeQuery();
//
//            //获取Page配置参数 分页参数数据 limit ?,?
//            Map<String,Object> map = (Map<String,Object>)boundSql.getParameterObject();
//            MPage mPage =  (MPage)map.get("page");
//
//            //count总数据填充
//            if(rs.next()){
//                mPage.setTotalPage(rs.getInt(1));
//            }
//
//            String pageSql = sql + "limit " + mPage.getCurrentPage() + "," + mPage.getPageSize();
//            //替换原sql更改为添加了支持分页的sql
//            metaObject.setValue("delegate.boundSql.sql", pageSql);
//        }
//        return invocation.proceed();
//    }
//
//    /**
//     * 拦截指定target
//     * 生成proxy代理
//     * */
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//    }
//}
