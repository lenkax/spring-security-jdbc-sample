package com.lucky.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.security.domain.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author: lenka
 * @date: 2023-05-03 11:28 AM
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByRoleIds(@Param("roleIds") List<Long> roleIds);
}
