package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class User {
    /**
     * 主键ID
     * 对应字段 : id
     */
//    @Setter
//    @Getter
    @TableId()
    private Long id;

    /**
     * 姓名
     * 对应字段 : nameg
     */
    private String name;

    /**
     * 年龄
     * 对应字段 : age
     */
    private Integer age;

    /**
     * 邮箱
     * 对应字段 : email
     */
    private String email;

    /**
     * 逻辑删除字段（1 删除 0 未删除）
     * 对应字段 : deleted
     */
    @TableLogic
    private Integer deleted;

    /*
     * 对应字段 : version
     */
    @Version
    //支持的数据类型只有 int, Integer, long ,Long, Date, Timestamp, LocalDateTime
    //仅支持updateById（id）与update(entity, wrapper)
    // 在update（entity，wrapper）方法下，wrapper不能复用
    //mybatis 乐观锁拦截器实质是修改ew的条件 增加version=？条件 重用时version时老version select／update不到数据
    private Long version;

    /**
     * 对应字段 : create_time
     */
    private Date createTime;

    /**
     * 对应字段 : update_time
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", deleted=" + deleted +
                ", version=" + version +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}