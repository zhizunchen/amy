package com.example.demo.imitate.annotation.service.impl;

import com.example.demo.imitate.annotation.dao.PersonDao;
import com.example.demo.imitate.annotation.itcast.ItcastResource;
import com.example.demo.imitate.annotation.service.PersonService;

/**
 * @Created by chenhe
 * @Date 2019-08-03 16:12
 * @Description
 */
public class PersonServiceImpl implements PersonService {

    @ItcastResource
    private PersonDao personDao;

    @Override
    public String saySomeThing() {
        return personDao.saySomeThing();
    }
}
