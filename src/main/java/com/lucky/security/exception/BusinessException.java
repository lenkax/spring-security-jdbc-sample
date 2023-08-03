package com.lucky.security.exception;

/**
 * @author: lenka
 * @date: 2023-05-04 10:10 AM
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int code = 500;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
