package com.lucky.security.convert;

import com.lucky.security.domain.dto.UserDTO;
import com.lucky.security.domain.model.User;
import org.mapstruct.Mapper;

/**
 * @author: lenka
 * @date: 2023-07-10 11:07 AM
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserConvert {

    User toEntity(UserDTO dto);

}
