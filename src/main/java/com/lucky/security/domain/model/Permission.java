package com.lucky.security.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表(权限表需要结合业务来设计，这里只给出简单的示例)
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "tb_permission")
public class Permission extends BaseEntity {

    /**
     * 权限名称
     */
    @TableField(value = "name")
    private String name;

    /**
     *
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 请求地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 请求方法
     */
    @TableField(value = "method")
    private String method;

    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

    /**
     * 状态 1 启用 0 禁用
     */
    @TableField(value = "status")
    private Integer status;
}
