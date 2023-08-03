package com.lucky.security.enums;

import lombok.Getter;

/**
 * @author: lenka
 * @date: 2023-07-07 2:38 PM
 */
@Getter
public enum RoleTypeEnum {

    /**
     * 超管
     */
    SUPER_ADMIN("SUPER_ADMIN", "超级管理员"),

    BUSINESS("BUSINESS", "业务管理员");

    /**
     * code
     */
    String code;

    /**
     * name
     */
    String name;

    RoleTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
