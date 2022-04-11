package com.liur.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liurui
 * @Description :用户实体类
 * @create 2022-03-14-9:01 下午
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId
    private Long id;
    private String username;
    private Integer age;
    private String email;
    private Integer version;
    private Integer deleted = 0;
    private Date createTime;
    private Date updateTime;
}
