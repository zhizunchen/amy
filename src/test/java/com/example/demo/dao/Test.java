package com.example.demo.dao;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.User;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Created by chenhe
 * @Date 2019-06-14 16:07
 * @Description TODO
 */
public class Test {

    public static void main(String[] args) {
//       Object optional = Optional.ofNullable(new String[]{"classpath:mybatis/mapper/*.xml"})
//               .orElse(new String[0]);
//
//        Stream.of(optional);

        PathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.isPattern("classpath:mybatis/mapper/*.xml".substring(10)));

//        Test test = new Test();
//        test.testOptional();

    }

    private void testOptional(){
        User user = new User();
        user.setAge(12);
        user.setName("chuchu");

        Optional<User> opt = Optional.ofNullable(user);
        System.out.println(opt.isPresent());

        Integer age = Optional.ofNullable(user)
                .map(u -> u.getAge())
                .orElse(0);
        System.out.println("age======" + age);

    }
}
