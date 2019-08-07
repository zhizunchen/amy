package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author chenhe
 * @since 2019-06-27
 * AR 测试
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_desc")
public class UserDesc extends Model<UserDesc> {

    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId
    private Long userId;

    private String descs;

    private Date createTime;

    private Date updateTime;

}
