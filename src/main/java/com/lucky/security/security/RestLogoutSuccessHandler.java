package com.lucky.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.security.cache.RedisTokenService;
import com.lucky.security.constant.HeaderConstant;
import com.lucky.security.util.R;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: lenka
 * @date: 2023-05-05 5:27 PM
 */
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisTokenService redisTokenService;

    public RestLogoutSuccessHandler(RedisTokenService redisTokenService) {
        this.redisTokenService = redisTokenService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader(HeaderConstant.HEADER_TOKEN);
        redisTokenService.removeToken(token);
        R<Void> result = new R<>();
        result.setCode(R.SUCCESS);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(mapper.writeValueAsString(result));
    }
}
