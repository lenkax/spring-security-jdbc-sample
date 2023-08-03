package com.lucky.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.security.domain.model.UserRole;
import com.lucky.security.mapper.UserRoleMapper;
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
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

    public List<UserRole> geteByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<UserRole>();
        queryWrapper.eq(UserRole::getUserId, userId);
        return super.list(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(Long userId,Long[] roleIds) {
        List<UserRole> userRoles = new ArrayList<UserRole>();
        Arrays.stream(roleIds).forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoles.add(userRole);
        });

        super.saveBatch(userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<UserRole>();
        queryWrapper.eq(UserRole::getUserId, userId);
        super.remove(queryWrapper);
    }
}
