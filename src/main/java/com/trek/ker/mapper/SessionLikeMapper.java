package com.trek.ker.mapper;

import com.trek.ker.entity.SessionLike;
import com.trek.ker.entity.dto.SessionLikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionLikeMapper {
    @Mapping(target = "sessionId", source = "session.id")
    @Mapping(target = "trailId", source = "trail.id")
    @Mapping(target = "userId", source = "user.id")
    SessionLikeDto toDto(SessionLike like);

    @Mapping(target = "session.id", source = "sessionId")
    @Mapping(target = "trail.id", source = "trailId")
    @Mapping(target = "user.id", source = "userId")
    SessionLike toEntity(SessionLikeDto dto);
}
