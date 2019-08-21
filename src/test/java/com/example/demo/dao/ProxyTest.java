package com.example.demo.dao;


import com.example.demo.DemoApplication;
import com.example.demo.analogschema.PersonService;
import com.example.demo.analogschema.PersonServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Created by chenhe
 * @Date 2019-08-21 09:25
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ProxyTest {

    @Resource
    private PersonServiceImpl personService;

    @Test
    public void testProxy(){

        System.out.println(personService.getClass().getName());
    }

}
