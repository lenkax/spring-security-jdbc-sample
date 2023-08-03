package com.lucky.security.cache;

import com.lucky.security.properties.ApplicationProperties;
import com.lucky.security.util.SecurityUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: lenka
 * @date: 2023-05-04 3:18 PM
 */
@Component
public class RedisTokenService {

    private final static String AUTHORITIES_KEY = "auth:";

    private final static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private long expiration = 3600;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ApplicationProperties applicationProperties;

    @PostConstruct
    public void init() {
        if (applicationProperties.getSecurity().getTokenValidityInSeconds() > 0) {
            expiration = applicationProperties.getSecurity().getTokenValidityInSeconds();
        }
    }

    public String createToken(Authentication authentication) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(AUTHORITIES_KEY.concat(token),
                authentication.getPrincipal(), expiration, DEFAULT_TIME_UNIT);
        return token;
    }

    public Boolean refreshExpiration(String token) {
        String key = AUTHORITIES_KEY.concat(token);
        return redisTemplate.expire(key, expiration, DEFAULT_TIME_UNIT);
    }

    public Boolean removeToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        String key = AUTHORITIES_KEY.concat(token);
        return redisTemplate.delete(key);
    }

    public Authentication getAuthentication(String token) {
        SecurityUser userDetails = (SecurityUser) redisTemplate.opsForValue().get(AUTHORITIES_KEY.concat(token));
        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
        }
        return null;
    }

    public Boolean validateToken(String token) {
        SecurityUser userDetails = (SecurityUser) redisTemplate.opsForValue().get(AUTHORITIES_KEY.concat(token));
        return userDetails != null;
    }
}
