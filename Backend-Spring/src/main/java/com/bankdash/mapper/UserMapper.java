package com.bankdash.mapper;

import com.bankdash.dto.UserDTO;
import com.bankdash.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toUserDTO(User user);
    User toUserEntity(UserDTO userDTO);
}

