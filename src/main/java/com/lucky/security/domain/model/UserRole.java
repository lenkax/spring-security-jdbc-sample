package com.lucky.security.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色表
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "tb_user_role")
public class UserRole extends BaseEntity {
    /**
     * 用户 ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色 ID
     */
    @TableField(value = "role_id")
    private Long roleId;
}
