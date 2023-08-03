package com.lucky.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.security.util.R;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: lenka
 * @date: 2023-05-05 3:00 PM
 */
public class LoginAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        R<String> result = new R<>();
        result.setCode(R.FORBIDDEN);
        result.setMsg("访问拒绝！");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(mapper.writeValueAsString(result));
    }
}
