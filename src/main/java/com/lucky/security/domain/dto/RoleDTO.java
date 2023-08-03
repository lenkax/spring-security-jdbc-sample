package com.lucky.security.domain.dto;

import lombok.Data;

/**
 * @author: lenka
 * @date: 2023-05-11 8:30 PM
 */
@Data
public class RoleDTO {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String description;
}
