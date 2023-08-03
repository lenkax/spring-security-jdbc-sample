package com.lucky.security.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.security.domain.model.Permission;
import com.lucky.security.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {

    @Resource
    private PermissionMapper permissionMapper;

    public List<Permission> findByRoleIds(List<Long> roleIds) {
      return  permissionMapper.findByRoleIds(roleIds);
    }

}
