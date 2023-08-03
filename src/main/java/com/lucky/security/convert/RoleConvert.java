package com.lucky.security.convert;

import com.lucky.security.domain.dto.RoleDTO;
import com.lucky.security.domain.model.Role;
import org.mapstruct.Mapper;

/**
 * @author: lenka
 * @date: 2023-07-10 11:07 AM
 */
@Mapper(componentModel = "spring", uses = {})
public interface RoleConvert {

    Role toEntity(RoleDTO dto);

}
