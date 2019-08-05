package com.example.demo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    User selectByIds(Long id);

    List<User> listByName(@Param("name") String name);

    List<User> selectAll(@Param(Constants.WRAPPER)Wrapper<User> wrapper);
}