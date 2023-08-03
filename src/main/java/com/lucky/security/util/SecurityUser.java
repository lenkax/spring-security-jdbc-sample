package com.lucky.security.util;

import com.lucky.security.domain.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: lenka
 * @date: 2023-05-04 11:26 AM
 */
@Data
public class SecurityUser implements UserDetails{

    private User user;

    private List<Long> roleIds;

    public SecurityUser(User user,List<Long> roleIds) {
        this.user = user;
        this.roleIds = roleIds;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roleIds.forEach(roleId -> authorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(roleId))));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
