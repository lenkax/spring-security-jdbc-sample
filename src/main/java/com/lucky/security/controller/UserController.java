package com.lucky.security.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.security.convert.UserConvert;
import com.lucky.security.domain.dto.UserDTO;
import com.lucky.security.domain.model.User;
import com.lucky.security.service.UserService;
import com.lucky.security.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: lenka
 * @date: 2023-05-05 09:30 AM
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserConvert userConvert;

    @Resource
    private UserService userService;

    @ApiOperation("分页")
    @GetMapping("/page")
    @PreAuthorize("@pa.hasPermission('user:page')")
    public R<Page<User>> page(@ApiParam("当前页") @RequestParam("current") Long currentPage, @ApiParam("每页条数") @RequestParam("size") Long pageSize,
                              @ApiParam("用户名") @RequestParam(name = "username",required = false) String username) {
        return R.success(userService.page(currentPage, pageSize, username));
    }

    @ApiOperation("明细")
    @GetMapping("/{id}")
    @PreAuthorize("@pa.hasPermission('user:detail')")
    public R<User> detail(@ApiParam("用户id") @PathVariable("id") String id) {
        return R.success(userService.getById(id));
    }

    @ApiOperation("新增")
    @PostMapping
    @PreAuthorize("@pa.hasPermission('user:add')")
    public R<Long> add(@RequestBody UserDTO dto) {
        User user = userConvert.toEntity(dto);
        return R.success(userService.add(user));
    }

    @ApiOperation("修改")
    @PutMapping("/{id}")
    @PreAuthorize("@pa.hasPermission('user:update')")
    public R<Void> update(@ApiParam("用户id") @PathVariable("id") Long id, @RequestBody UserDTO dto) {
        User user = userConvert.toEntity(dto);
        user.setId(id);
        userService.update(user);
        return R.success();
    }

    @ApiOperation("修改密码")
    @PutMapping("/updatePassword")
    @PreAuthorize("@pa.hasPermission('user:updatePassword')")
    public R<Void> updatePassword(@ApiParam("用户id")String id, @ApiParam("旧密码")String oldPassword, @ApiParam("新密码")String newPassword) {
        userService.updatePassword(id, oldPassword, newPassword);
        return R.success();
    }
    @ApiOperation("设置角色")
    @PutMapping("/setRole")
    @PreAuthorize("@pa.hasPermission('user:setRole')")
    public R<Void> setRole(@ApiParam("用户id") Long id, @ApiParam("角色id") Long[] roleIds) {
        userService.setRole(id, roleIds);
        return R.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pa.hasPermission('user:delete')")
    public R<Void> delete(@ApiParam("用户id") @PathVariable("id") Long id) {
        userService.removeById(id);
        return R.success();
    }
}
