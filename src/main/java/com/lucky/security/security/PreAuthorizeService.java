package com.lucky.security.security;

import com.lucky.security.domain.model.Permission;
import com.lucky.security.service.PermissionService;
import com.lucky.security.service.RoleService;
import com.lucky.security.util.SecurityUser;
import com.lucky.security.util.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: lenka
 * @date: 2023-05-11 9:06 PM
 */
@Component("pa")
public class PreAuthorizeService {

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    public boolean hasPermission(String... permissions) {
        // 获取当前登录用户的信息
        Optional<SecurityUser> principal = SecurityUtils.getCurrentUser();
        if (!principal.isPresent()) {
            return false;
        }

        // 获取当前登录用户拥有的角色
        List<Long> roleIds = principal.get().getRoleIds();
        // 判断是否是超级管理员
        boolean isSuperAdmin = roleService.hasAnySuperAdmin(roleIds);
        // 如果是超管，则放行
        if (isSuperAdmin) {
            return true;
        }

        // 获取角色拥有的权限
        List<String> userPermissions = permissionService.findByRoleIds(roleIds).stream()
                .map(Permission::getPerms)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 判断是否拥有权限
        return Arrays.stream(permissions).anyMatch(userPermissions::contains);
    }
}

//    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
//    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//        boolean hasPermission = false;
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails) {
//            SecurityUser securityUser = (SecurityUser) principal;
//            List<Long> roleIds = securityUser.getRoleIds();
//            boolean isSuperAdmin = roleService.hasAnySuperAdmin(roleIds);
//            if (isSuperAdmin) {
//                return true;
//            }
//
//            List<String> permissions = permissionService.findByRoleIds(roleIds).stream().map(Permission::getUrl).collect(Collectors.toList());
//            for (String permission : permissions) {
//                if (ANT_PATH_MATCHER.match(permission, request.getRequestURI())) {
//                    hasPermission = true;
//                    break;
//                }
//            }
//        }
//        return hasPermission;
//    }
