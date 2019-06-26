package com.example.demo.autoconfigure;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Created by chenhe
 * @Date 2019-06-24 15:24
 * @Description TODO
 */

@Slf4j
@Data
public class MybatisPlusAutoConfigureUtil {

    private String name;

    private Integer age;

    public MybatisPlusAutoConfigureUtil() {
        log.info("==========================");
    }

    @Override
    public String toString() {
        return "MybatisPlusAutoConfigureUtil{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
