package com.lucky.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.security.domain.model.RolePermission;
import com.lucky.security.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> {

    @Transactional(rollbackFor = Exception.class)
    public void setRolePermission(Long roleId, Long[] permissionIds) {
        deleteByRoleId(roleId);

        if (permissionIds == null) {
            return;
        }

        List<RolePermission> rolePermissions = new ArrayList<>();
        Arrays.stream(permissionIds).forEach(permissionId -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        });
        super.saveBatch(rolePermissions);
    }

    public void deleteByRoleId(Long roleId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        super.remove(queryWrapper);
    }
}
