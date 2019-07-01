package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.User;

public interface UserMapper extends BaseMapper<User> {


    User selectByIds(Long id);
}