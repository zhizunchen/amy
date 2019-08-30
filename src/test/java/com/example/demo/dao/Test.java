package com.example.demo.dao;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.fastjson.JSON;
import com.example.demo.domain.User;
import com.example.demo.interceptor.PrepareInterceptor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.*;
import java.util.stream.Stream;

/**
 * @Created by chenhe
 * @Date 2019-06-14 16:07
 * @Description TODO
 */
public class Test {

    public static void main(String[] args) {
//        try {
//            if(null != Test.class.forName("com.example.demo.dao.UserMapper")){
//                System.out.println("1234567890-");
//            }else{
//                System.out.println("qwertyuiop");
//            }
//
//        }catch (Exception e){}

        Zi zi = new Zi("12");
        zi.testAsList();

    }

    private void testOptional(){
//        User user = new User();
//        user.setAge(12);
//        user.setName("chuchu");
//
//        Optional<User> opt = Optional.ofNullable(user);
//        System.out.println(opt.isPresent());
//
//        Integer age = Optional.ofNullable(user)
//                .map(u -> u.getAge())
//                .orElse(0);
//        System.out.println("age======" + age);

    }
}


class Fu{
    public String date;
    public Fu(String date) {
        System.out.println("=======");
    }
}
class Zi{

    public String date;
    public Zi(String date) {
        super();
        this.date = date;
        final String d;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zi zi = (Zi) o;
        return date.equals(zi.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    public void input(){
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    public void equalsTest(){
        Objects.equals(null, "SnailCLib");// null == null
    }
    public void testAsList(){
        String [] myArr = {"apple", "orange"};
        List<String> myList = Arrays.asList(myArr);
        myList.add("123");
        System.out.println(myList);
    }

}


