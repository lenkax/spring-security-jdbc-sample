package com.lucky.security.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户表
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "tb_user")
public class User extends BaseEntity {
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 注册手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 注册邮箱
     */
    @TableField(value = "email")
    private String email;
}
