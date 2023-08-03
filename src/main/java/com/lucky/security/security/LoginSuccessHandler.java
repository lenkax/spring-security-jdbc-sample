package com.lucky.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.security.cache.RedisTokenService;
import com.lucky.security.domain.model.User;
import com.lucky.security.util.R;
import com.lucky.security.util.SecurityUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RedisTokenService redisTokenService;

    public LoginSuccessHandler(RedisTokenService redisTokenService) {
        this.redisTokenService = redisTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = redisTokenService.createToken(authentication);
        User user = ((SecurityUser) authentication.getPrincipal()).getUser();
        LoginDTO loginDTO = LoginDTO.builder()
                .accessToken(token)
                .username(user.getUsername())
                .phone(user.getPhone())
                .build();

        R<LoginDTO> result = new R<>();
        result.setCode(R.SUCCESS);
        result.setData(loginDTO);

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().append(mapper.writeValueAsString(result));
        } catch (IOException e) {
            throw new BadCredentialsException("登录异常：" + e.getMessage());
        }
    }

    @Data
    @Builder
    public static class LoginDTO {
        private String accessToken;
        private String username;
        private String phone;
    }
}
