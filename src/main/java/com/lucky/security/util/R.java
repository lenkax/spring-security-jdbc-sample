package com.lucky.security.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author: lenka
 * @date: 2023-05-04 2:25 PM
 */
@Getter
@Setter
public class R<T> implements Serializable {

    /**
     * 业务逻辑成功
     */
    public static final Integer SUCCESS = 200;

    /**
     * 参数校验异常
     */
    public static final Integer ERROR = 400;
    /**
     * 认证失败
     */
    public static final Integer UNAUTHORIZED = 401;
    /**
     * 禁止访问
     */
    public static final Integer FORBIDDEN = 403;

    /**
     * 系统异常
     */
    public static final Integer INTERNAL_SERVER_ERROR = 500;


    private int code;

    private String msg;

    private T data;

    public R() {
    }

    public R(int code) {
        this.code = code;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> success() {
        return new R<>(HttpStatus.OK.value());
    }

    public static <T> R<T> success(T data) {
        return new R<>(HttpStatus.OK.value(), "", data);
    }

    public static <T> R<T> error() {
        return new R<>(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static <T> R<T>  error(int code, String message) {
        return new R(code, message);
    }

    public static <T> R<T>  error(int code, String message, T data) {
        return new R<>(code, message, data);
    }
}
