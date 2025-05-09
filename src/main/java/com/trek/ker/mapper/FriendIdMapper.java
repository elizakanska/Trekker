package com.trek.ker.mapper;

import com.trek.ker.entity.id.FriendId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FriendIdMapper {
    default Long map(FriendId friendId) {
        return friendId != null ? friendId.getFriendId() : null;
    }
}
