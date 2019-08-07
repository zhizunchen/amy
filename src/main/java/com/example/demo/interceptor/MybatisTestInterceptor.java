package com.example.demo.interceptor;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.*;
import lombok.Data;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Created by chenhe
 * @Date 2019-06-05 10:38
 * @Description mybatis 乐观锁拦截器Demo
 */
@Intercepts({
        @Signature(
            type = Executor.class, //拦截那个接口
            method = "update",     //接口内的那个方法名
            args = {MappedStatement.class, Object.class}//拦截的方法的入参，按顺序
        )})

public class MybatisTestInterceptor implements Interceptor {

    public static final String MP_OPTLOCK_VERSION_ORIGINAL = "MP_OPTLOCK_VERSION_ORIGINAL";

    public static final String MP_OPTLOCK_VERSION_COLUMN = "MP_OPTLOCK_VERSION_COLUMN";

    public static final String MP_OPTLOCK_ET_ORIGINAL = "MP_OPTLOCK_ET_ORIGINAL";

    private static final String NAME_ENTITY = Constants.ENTITY;

    private static final String NAME_ENTITY_WRAPPER = Constants.WRAPPER;

    private static final String PARAM_UPDATE_METHOD_NAME = "update";

    private final Map<Class<?>, EntityField> versionFieldCache = new ConcurrentHashMap<>();

    private final Map<Class<?>, List<EntityField>> entityFieldsCache = new ConcurrentHashMap<>();


    public void test(){
// XMLConfigBuilder
// XMLConfigBuilder是对mybatis的配置文件进行解析的类，会对myabtis解析后的信息存放在Configuration对象中


    }

    // 这里是每次执行操作的时候，都会进行这个拦截器的方法内
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if(SqlCommandType.UPDATE != ms.getSqlCommandType()){
            return invocation.proceed();
        }

        Object param = args[1];

        //wrapper = ew
        AbstractWrapper ew = null;
        //entity = et
        Object et = null;
        if(param instanceof Map){
            Map map = (Map)param;
            if(map.containsKey(NAME_ENTITY)){
                et = map.get(NAME_ENTITY);
            }
            if(map.containsKey(NAME_ENTITY_WRAPPER)){
                ew = (AbstractWrapper)map.get(NAME_ENTITY_WRAPPER);
            }
            if(null != et){
                String methodId = ms.getId();
                String methodName = methodId.substring(methodId.lastIndexOf(StringPool.DOT) + 1);
                Class<?> entityClass = et.getClass();
                TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
                while(null == tableInfo && null != entityClass){
                    entityClass = ClassUtils.getUserClass(entityClass.getSuperclass());
                    tableInfo = TableInfoHelper.getTableInfo(entityClass);
                }

                EntityField versionField = this.getVersionField(entityClass, tableInfo);
                if(null == versionField){
                    return invocation.proceed();
                }
                Field field = versionField.getField();
                Object originalVersionVal = versionField.getField().get(et);
                Object updatedVersionVal = getUpdatedVersionVal(originalVersionVal);

                if(PARAM_UPDATE_METHOD_NAME.equals(methodName)){
                    if(null != originalVersionVal){
                        if(null == ew){
                            UpdateWrapper uw = new UpdateWrapper();
                            uw.eq(versionField.getColumnName(), originalVersionVal);
                            map.put(NAME_ENTITY_WRAPPER, uw);
                            field.set(et, updatedVersionVal);
                        }else{
                            ew.apply(versionField.getColumnName() + "={0}", originalVersionVal);
                            field.set(et, updatedVersionVal);
                        }
                    }
                    return invocation.proceed();
                }else{
                    dealUpdateById(entityClass, et, versionField, originalVersionVal, updatedVersionVal, map);
                    Object resultObj = invocation.proceed();
                    if (resultObj instanceof Integer) {
                        Integer effRow = (Integer) resultObj;
                        if (updatedVersionVal != null && effRow != 0 && field != null) {
                            //updated version value set to entity.
                            field.set(et, updatedVersionVal);
                        }
                    }
                    return resultObj;
                }
            }
        }
        return invocation.proceed();
    }

    /**
     * 获取实体的反射属性(类似getter)
     *
     * @param parameterClass ignore
     * @return ignore
     */
    private List<EntityField> getEntityFields(Class<?> parameterClass) {
        if (entityFieldsCache.containsKey(parameterClass)) {
            return entityFieldsCache.get(parameterClass);
        }
        List<EntityField> fields = this.getFieldsFromClazz(parameterClass);
        entityFieldsCache.put(parameterClass, fields);
        return fields;
    }

    private List<EntityField> getFieldsFromClazz(Class<?> parameterClass) {
        return ReflectionKit.getFieldList(parameterClass).stream().map(field -> {
            field.setAccessible(true);
            return field.isAnnotationPresent(Version.class) ? new EntityField(field, true) : new EntityField(field, false);
        }).collect(Collectors.toList());
    }

    /**
     * 处理updateById(entity)乐观锁逻辑
     *
     * @param entityClass        实体类
     * @param et                 参数entity
     * @param entityVersionField ignore
     * @param originalVersionVal 原来版本的value
     * @param updatedVersionVal  乐观锁自动更新的新value
     * @param map
     */
    @SuppressWarnings("unchecked")
    private void dealUpdateById(Class<?> entityClass, Object et, EntityField entityVersionField,
                                Object originalVersionVal, Object updatedVersionVal, Map map) throws IllegalAccessException {
        if (originalVersionVal == null) {
            return;
        }
        List<EntityField> fields = getEntityFields(entityClass);
        Map<String, Object> entityMap = new HashMap<>();
        for (EntityField ef : fields) {
            Field fd = ef.getField();
            entityMap.put(fd.getName(), fd.get(et));
        }
        Field versionField = entityVersionField.getField();
        String versionColumnName = entityVersionField.getColumnName();
        //update to cache
        entityVersionField.setColumnName(versionColumnName);
        entityMap.put(versionField.getName(), updatedVersionVal);
        entityMap.put(MP_OPTLOCK_VERSION_ORIGINAL, originalVersionVal);
        entityMap.put(MP_OPTLOCK_VERSION_COLUMN, versionColumnName);
        entityMap.put(MP_OPTLOCK_ET_ORIGINAL, et);
        map.put(NAME_ENTITY, entityMap);
    }

    protected Object getUpdatedVersionVal(Object originalVersionVal) {
        if (null == originalVersionVal) {
            return null;
        }
        Class<?> versionValClass = originalVersionVal.getClass();
        if (long.class.equals(versionValClass)) {
            return ((long) originalVersionVal) + 1;
        } else if (Long.class.equals(versionValClass)) {
            return ((Long) originalVersionVal) + 1;
        } else if (int.class.equals(versionValClass)) {
            return ((int) originalVersionVal) + 1;
        } else if (Integer.class.equals(versionValClass)) {
            return ((Integer) originalVersionVal) + 1;
        } else if (Date.class.equals(versionValClass)) {
            return new Date();
        } else if (Timestamp.class.equals(versionValClass)) {
            return new Timestamp(System.currentTimeMillis());
        } else if (LocalDateTime.class.equals(versionValClass)) {
            return LocalDateTime.now();
        }
        //not supported type, return original val.
        return originalVersionVal;
    }

    /**
     * 反射检查参数类是否启动乐观锁
     *
     * @param parameterClass 实体类
     * @param tableInfo      实体数据库反射信息
     * @return ignore
     */
    private EntityField getVersionFieldRegular(Class<?> parameterClass, TableInfo tableInfo) {
        return Object.class.equals(parameterClass) ? null : ReflectionKit.getFieldList(parameterClass).stream().filter(e -> e.isAnnotationPresent(Version.class)).map(field -> {
            field.setAccessible(true);
            return new EntityField(field, true, tableInfo.getFieldList().stream().filter(e -> field.getName().equals(e.getProperty())).map(TableFieldInfo::getColumn).findFirst().orElse(null));
        }).findFirst().orElseGet(() -> this.getVersionFieldRegular(parameterClass.getSuperclass(), tableInfo));
    }

    private EntityField getVersionField(Class<?> parameterClass, TableInfo tableInfo) {
        synchronized (parameterClass.getName()) {
            if (versionFieldCache.containsKey(parameterClass)) {
                return versionFieldCache.get(parameterClass);
            }
            // 缓存类信息
            EntityField field = this.getVersionFieldRegular(parameterClass, tableInfo);
            if (field != null) {
                versionFieldCache.put(parameterClass, field);
                return field;
            }
            return null;
        }
    }

    @Data
    private class EntityField {

        EntityField(Field field, boolean version) {
            this.field = field;
            this.version = version;
        }

        public EntityField(Field field, boolean version, String columnName) {
            this.field = field;
            this.version = version;
            this.columnName = columnName;
        }

        private Field field;
        private boolean version;
        private String columnName;

    }
    // 主要是为了把这个拦截器生成一个代理放到拦截器链中
    //必须具体方法
    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    // 插件初始化的时候调用，也只调用一次，插件配置的属性从这里设置进来
    @Override
    public void setProperties(Properties properties) {

    }

}
