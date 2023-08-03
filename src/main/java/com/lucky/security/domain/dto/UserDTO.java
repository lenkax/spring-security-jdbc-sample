package com.lucky.security.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: lenka
 * @date: 2023-05-05 4:27 PM
 */
@Getter
@Setter
public class UserDTO {

    @ApiModelProperty("用户名")
    private String username;
    
    @ApiModelProperty("密码")
    private String password;
    
    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;
}
