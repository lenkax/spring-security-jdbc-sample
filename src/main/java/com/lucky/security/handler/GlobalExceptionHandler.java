package com.lucky.security.handler;

import com.lucky.security.exception.BusinessException;
import com.lucky.security.util.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lenka
 * @date: 2023-05-04 2:25 PM
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public R<?> handleBusinessException(BusinessException ex) {
        return R.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public R<?> handleException(Exception ex) {
        return R.error();
    }

    @ExceptionHandler(AuthenticationException.class)
    public R<?> handleAuthenticationException(AuthenticationException ex) {
        return R.error(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public R<?> handleAccessDeniedException(AccessDeniedException ex) {
        return R.error(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(e.getField(), e.getDefaultMessage());
                    return map;
                })
                .collect(Collectors.toList());

        return R.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }
}
