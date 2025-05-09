package com.trek.ker.mapper;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.dto.FriendDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FriendIdMapper.class})
public interface FriendMapper {
    @Mapping(target = "user1Id", source = "id.user1Id")
    @Mapping(target = "friendId", source = "id.friendId")
    FriendDto toDto(Friend friend);

    @Mapping(target = "id", expression = "java(new FriendId(dto.getUser1Id(), dto.getFriendId()))")
    @Mapping(target = "user1.id", source = "user1Id")
    @Mapping(target = "friend.id", source = "friendId")
    Friend toEntity(FriendDto dto);
}

