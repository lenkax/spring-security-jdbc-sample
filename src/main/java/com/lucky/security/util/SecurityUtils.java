package com.lucky.security.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author:
 * @date: 2023-05-04 10:52 AM
 */
@UtilityClass
public class SecurityUtils {
    public static String getCurrentUsername() {
        Optional<SecurityUser> userOptional = getCurrentUser();
        return userOptional.map(SecurityUser::getUsername).orElse(null);
    }

    public static Long getCurrentUserId() {
        Optional<SecurityUser> userOptional = getCurrentUser();
        return userOptional.map(securityUser -> securityUser.getUser().getId()).orElse(null);
    }
    public Optional<SecurityUser> getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractUser(securityContext.getAuthentication()));
    }

    public SecurityUser extractUser(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof SecurityUser) {
            return (SecurityUser) authentication.getPrincipal();
        }
        return null;
    }
}
