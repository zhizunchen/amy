package com.example.demo.annotation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @Created by chenhe
 * @Date 2019-06-11 09:46
 * @Description 应用了注解类的类
 *              使用具体注解的解析
 */
@Data
public class AnnotationObjDemo {

    @CustomCheck(required = true, type = "A", digits = 12)
    public String name;

    @CustomCheck(required = true, type = "N", digits = 3)
    public Integer age;

    // 解析
    public static void main(String[] args) {
        AnnotationObjDemo demo = new AnnotationObjDemo();
        demo.setAge(1212);
        demo.setName("lily");
        try {
            demo.check(demo);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void check(Object demo) throws Exception{

        Class cl = demo.getClass();

        for (Field field : cl.getFields()){

            //获取字段具体值
            Object object = field.get(demo);

            //判断字段是否被制定注解 CustomCheck 修饰
            //如果此元素上存在指定类型的注释，则返回true 否则返回false
            Boolean bol = field.isAnnotationPresent(CustomCheck.class);
            if(bol){
                //获取制定注解的属性
                //返回该元素的，如果这样的注释，否则返回null指定类型的注释
                CustomCheck annotation = (CustomCheck )field.getAnnotations()[0];
                //
                Boolean required = annotation.required();
                //
                String type = annotation.type();
                //
                int digits = annotation.digits();


                //1 根据制定注解的属性过滤
                if(!required){
                    return;
                }else {
                    if(StringUtils.isEmpty(object)){
                        throw new Exception("请求参数为必填");
                    }
                }

                // 2
                if("N".equals(type)){
                    Class cls = field.getType();
                    if(null != object &&(cls.isAssignableFrom(Integer.class) || cls.isAssignableFrom(Long.class))){
                        String regex = "^([0-9]{1," + digits + "})$";
                        if(!String.valueOf(object).matches(regex)){
                            throw new Exception("参数长度不符合指定长度！");
                        }
                    }
                }else if("A".equals(type)){
                    String str = (String)object;
                    Class cls = field.getType();
                    if (null != object && !cls.isAssignableFrom(String.class)) {
                        throw new Exception("参数不符合制定数据类型！");
                    }
                }
            }
        }
    }
}
