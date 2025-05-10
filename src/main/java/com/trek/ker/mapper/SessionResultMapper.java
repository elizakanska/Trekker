package com.trek.ker.mapper;

import com.trek.ker.entity.SessionResult;
import com.trek.ker.entity.dto.SessionResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionResultMapper {

    @Mapping(target = "sessionUser1Id", source = "session.id.user1Id")
    @Mapping(target = "sessionUser2Id", source = "session.id.user2Id")
    @Mapping(target = "trailId", source = "trail.id")
    @Mapping(target = "finalRank", source = "finalRank")
    SessionResultDto toDto(SessionResult result);

    @Mapping(target = "session.id.user1Id", source = "sessionUser1Id")
    @Mapping(target = "session.id.user2Id", source = "sessionUser2Id")
    @Mapping(target = "trail.id", source = "trailId")
    @Mapping(target = "finalRank", source = "finalRank")
    SessionResult toEntity(SessionResultDto dto);
}
