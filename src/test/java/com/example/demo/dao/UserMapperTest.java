package com.example.demo.dao;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.DemoApplication;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Update;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Created by chenhe
 * @Date 2019-06-04 16:02
 * @Description 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testOne(){

        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        List<User> list = userMapper.selectList(wrapper);

//        SELECT
//                id,
//                name,
//                age,
//                email,
//                deleted
//        FROM
//                user
//        WHERE
//                deleted=0

        list.forEach(integer ->{
            System.out.println(integer);
        });

//        list.stream()
//            .filter(item -> item.getAge().equals(28))
//            .forEach(item ->{
//              System.out.println(item.getAge());
//            });
    }

    @Test
    public void testDeleteLogic(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("id", 1);
        userMapper.delete(wrapper);

    }

    @Test
    public void testUpdate(){
        Wrapper<User> wrapper = new UpdateWrapper<User>()
                .eq("id", 1137994295025885185L);

        User user = new User();
        user.setName("testUpdate1");
        user.setVersion(7L);

        user = Optional.ofNullable(user).orElse(new User());

        int index = userMapper.update(user, wrapper);

        System.out.println("update index = " + index);

//        System.out.println(JSON.toJSON(wrapper));
//        System.out.println(wrapper.getSqlSegment());
//        System.out.println(wrapper.getSqlSet());
//        System.out.println(wrapper.getSqlSelect());
        System.out.println(JSON.toJSON(userMapper.selectOne(wrapper)));
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("lily1");
        user.setAge(12);
        user.setEmail("11111111");

        userMapper.insert(user);
    }

}