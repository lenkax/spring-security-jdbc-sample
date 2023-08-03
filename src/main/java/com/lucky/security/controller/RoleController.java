package com.lucky.security.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.security.convert.RoleConvert;
import com.lucky.security.domain.dto.RoleDTO;
import com.lucky.security.domain.model.Role;
import com.lucky.security.enums.RoleTypeEnum;
import com.lucky.security.service.RolePermissionService;
import com.lucky.security.service.RoleService;
import com.lucky.security.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author: lenka
 * @date: 2023-05-05 09:01 AM
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Resource
    private RoleConvert roleConvert;

    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @ApiOperation("分页")
    @GetMapping("/page")
    @PreAuthorize("@pa.hasPermission('role:page')")
    public R<Page<Role>> page(@ApiParam("当前页") @RequestParam("current") Long currentPage,
                              @ApiParam("每页条数") @RequestParam("size") Long pageSize,
                              @ApiParam("角色名") @RequestParam(name = "name",required = false) String name) {
        return R.success(roleService.page(currentPage, pageSize, name));
    }

    @ApiOperation("明细")
    @GetMapping("/{id}")
    @PreAuthorize("@pa.hasPermission('role:detail')")
    public R<Role> detail(@ApiParam("角色id") @PathVariable("id") String id) {
        return R.success(roleService.getById(id));
    }

    @ApiOperation("新增")
    @PostMapping
    @PreAuthorize("@pa.hasPermission('role:add')")
    public R<Boolean> add(@RequestBody RoleDTO dto) {
        Role role = roleConvert.toEntity(dto);
        role.setType(RoleTypeEnum.BUSINESS.getCode());
        return R.success(roleService.save(role));
    }

    @ApiOperation("修改")
    @PutMapping("/{id}")
    @PreAuthorize("@pa.hasPermission('role:update')")
    public R<Void> update(@ApiParam("角色id") @PathVariable("id") Long id, @RequestBody RoleDTO dto) {
        Role role = roleConvert.toEntity(dto);
        role.setId(id);
        roleService.updateById(role);
        return R.success();
    }

    @ApiOperation("设置权限")
    @PutMapping("/setPermission")
    @PreAuthorize("@pa.hasPermission('role:setPermission')")
    public R<Void> setPermission(@ApiParam("角色id") Long id, @ApiParam("权限id") Long[] permissionIds) {
        rolePermissionService.setRolePermission(id, permissionIds);
        return R.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pa.hasPermission('role:delete')")
    public R<Void> delete(@ApiParam("角色id") @PathVariable("id") Long id) {
        roleService.removeById(id);
        return R.success();
    }
}
