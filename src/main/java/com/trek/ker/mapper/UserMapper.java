package com.trek.ker.mapper;

import com.trek.ker.entity.*;
import com.trek.ker.entity.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
}

