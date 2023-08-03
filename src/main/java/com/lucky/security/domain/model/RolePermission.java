package com.lucky.security.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限表
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "tb_role_permission")
public class RolePermission extends BaseEntity {
    /**
     * 角色 ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限 ID
     */
    @TableField(value = "permission_id")
    private Long permissionId;
}
