package com.example.demo.dao;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.DemoApplication;
import com.example.demo.domain.User;
import com.example.demo.domain.UserDesc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
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


    @Resource
    private UserDescMapper descMapper;

    @Test
    public void test(){



        System.out.println(userMapper.selectByIds(1L));
    }

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

        System.out.println(JSON.toJSON(userMapper.selectOne(wrapper)));
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("lily2");
        user.setAge(12);
        user.setEmail("11111111");

        System.out.println(user.getId());
        userMapper.insert(user);
        System.out.println(user.getId());
    }


    @Test
    public void testLambda(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getAge, 12)
                .select(User::getName, null);

    }

    @Test
    public void testTableId(){

        System.out.println(descMapper.selectById(1L));

    }

    @Test
    public void testSelectPage(){

        /**
         * Preparing: SELECT COUNT(1) FROM user WHERE deleted = 0 AND age = ?
         * Parameters: 12(Integer)
         * Columns: COUNT(1)
         * Row: 2
         *
         * Preparing: SELECT id,name,age,email,deleted,version,create_time,update_time FROM user WHERE deleted=0 AND age = ? LIMIT ?,?
         * Parameters: 12(Integer), 0(Long), 10(Long)
         * Columns: id, name, age, email, deleted, version, create_time, update_time
         * Row: 1137919890727968770, lily, 12, 12345678, 0, 1, null, null
         * Row: 1137994295025885185, 7e92479c5ad6096640abc7d1f3dea527, 12, 11111111, 0, 8, 2019-06-10 03:05:47, 2019-06-12 03:34:13
         * Total: 2
         *
         * */
        // isSearchCount 默认为true 执行select COUNT(1) FROM user WHERE deleted = 0 and age = ?
        IPage<User> ipage = new Page<>(1, 10, false);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getAge, 12);

        IPage<User> page =  userMapper.selectPage(ipage, wrapper);

        System.out.println(page.getTotal());
        ipage.getRecords().forEach(user -> System.out.println(user));

    }

    @Test
    public void updateById(){
        User user = new User();
        user.setId(1L);
        user.setName("updateById");
        int rows = userMapper.updateById(user);
        System.out.println("影响行数： " + rows);
    }

    @Test
    public void updateByWrapper(){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, 1L)
                .set(User::getName, "lambdatest");


        int rows = userMapper.update(new User(), wrapper);
        System.out.println("影响行数： " + rows);
    }

    @Test
    public void testAR(){
        UserDesc desc = new UserDesc();
        desc.setUserId(1L);
        desc.insert();
    }

}