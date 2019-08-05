package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @Created by chenhe
 * @Date 2019-05-30 18:14
 * @Description
 */
@Service
public class ThreadPoolServiceImpl {

//    @Resource
    @Autowired
    private ExecutorService executorService;



}
