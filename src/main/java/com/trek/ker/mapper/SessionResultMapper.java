package com.trek.ker.mapper;

import com.trek.ker.entity.SessionResult;
import com.trek.ker.entity.dto.SessionResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionResultMapper {
    @Mapping(target = "sessionId", source = "session.id")
    @Mapping(target = "trailId", source = "trail.id")
    @Mapping(target = "finalRank", source = "finalRank")
    SessionResultDto toDto(SessionResult result);

    @Mapping(target = "session.id", source = "sessionId")
    @Mapping(target = "trail.id", source = "trailId")
    SessionResult toEntity(SessionResultDto dto);
}
