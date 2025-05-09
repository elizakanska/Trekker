package com.trek.ker.mapper;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.dto.FriendDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FriendMapper {
    @Mapping(target = "user1Id", source = "user1.id")
    @Mapping(target = "friendId", source = "friend.id")
    @Mapping(target = "friend", source = "friend")
    FriendDto toDto(Friend friend);

    @Mapping(target = "user1.id", source = "user1Id")
    @Mapping(target = "friend.id", source = "friendId")
    Friend toEntity(FriendDto dto);
}

