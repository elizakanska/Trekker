package com.trek.ker.mapper;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.dto.FriendDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FriendMapper {
    FriendDto toDto(Friend friend);
    Friend toEntity(FriendDto dto);
}
