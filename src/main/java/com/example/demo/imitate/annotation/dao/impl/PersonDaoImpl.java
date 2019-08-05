package com.example.demo.imitate.annotation.dao.impl;

import com.example.demo.imitate.annotation.dao.PersonDao;

/**
 * @Created by chenhe
 * @Date 2019-08-03 16:16
 * @Description
 */
public class PersonDaoImpl implements PersonDao {

    @Override
    public String saySomeThing() {
        return "hello world";
    }
}
