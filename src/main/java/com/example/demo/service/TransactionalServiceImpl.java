package com.example.demo.service;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Created by chenhe
 * @Date 2019-05-30 18:54
 * @Description Transactional
 */

@Service
class TransactionalServiceImpl {

    @Transactional(rollbackFor = Exception.class)
    public void test() {

    }

}
class Demo{

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void test() throws IllegalAccessException{
        System.out.println("========");
        throw new IllegalAccessException("23456789");

    }

}
