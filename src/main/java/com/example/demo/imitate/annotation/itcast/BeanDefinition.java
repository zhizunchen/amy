package com.example.demo.imitate.annotation.itcast;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by chenhe
 * @Date 2019-08-03 16:21
 * @Description 将读取到的bean信息保存到一个JavaBean对象中
 */
@Data
public class BeanDefinition {

    private String id;

    private String className;

}
