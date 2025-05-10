package com.trek.ker.mapper;

import com.trek.ker.entity.SessionLike;
import com.trek.ker.entity.dto.SessionLikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionLikeMapper {

    @Mapping(target = "sessionUser1Id", source = "session.id.user1Id")
    @Mapping(target = "sessionUser2Id", source = "session.id.user2Id")
    @Mapping(target = "trailId", source = "trail.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "liked", source = "liked")
    @Mapping(target = "round", source = "round")
    SessionLikeDto toDto(SessionLike like);

    @Mapping(target = "session.id.user1Id", source = "sessionUser1Id")
    @Mapping(target = "session.id.user2Id", source = "sessionUser2Id")
    @Mapping(target = "trail.id", source = "trailId")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "liked", source = "liked")
    @Mapping(target = "round", source = "round")
    SessionLike toEntity(SessionLikeDto dto);
}
