package com.lucky.security.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "tb_role")
public class Role extends BaseEntity {

    /**
     * 角色类型
     */
    @TableField(value = "type")
    private String type;


    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;


    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

}
