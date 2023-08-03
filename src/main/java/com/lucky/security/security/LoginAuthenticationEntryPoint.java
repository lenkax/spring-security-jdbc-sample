package com.lucky.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.security.util.R;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: lenka
 * @date: 2023-05-05 3:00 PM
 */
public class LoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {;
        R<String> result = new R<>();
        result.setCode(R.UNAUTHORIZED);
        result.setMsg("未登录或登录已过期，请重新登录！");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(mapper.writeValueAsString(result));
    }
}
