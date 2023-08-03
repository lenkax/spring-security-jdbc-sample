package com.lucky.security.security;

import com.lucky.security.domain.model.User;
import com.lucky.security.domain.model.UserRole;
import com.lucky.security.service.UserRoleService;
import com.lucky.security.service.UserService;
import com.lucky.security.util.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: lenka
 * @date: 2023-05-04 2:53 PM
 */
@Service
public class DomainUserDetailService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userService.getByUsername(username))
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
    }
    public UserDetails createUserDetails(User user) {
        List<Long> roleIds = userRoleService.geteByUserId(user.getId()).stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        return new SecurityUser(user, roleIds);
    }
}
