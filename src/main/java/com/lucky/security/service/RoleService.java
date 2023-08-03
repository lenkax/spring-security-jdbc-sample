package com.lucky.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.security.domain.model.Role;
import com.lucky.security.enums.RoleTypeEnum;
import com.lucky.security.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    public Page<Role> page(Long currentPage, Long pageSize, String name) {
        Page<Role> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Role::getName, name);
        return super.page(page, queryWrapper);
    }

    public boolean hasAnySuperAdmin(List<Long> roleIds){
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Role::getId, roleIds);
        List<Role> roles=super.list(queryWrapper);
        return roles.stream().anyMatch(role -> RoleTypeEnum.SUPER_ADMIN.getCode().equals(role.getType()));
    }
}
