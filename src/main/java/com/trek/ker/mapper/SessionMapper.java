package com.trek.ker.mapper;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.dto.SessionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "user1Id", source = "id.user1Id")
    @Mapping(target = "user2Id", source = "id.user2Id")
    SessionDto toDto(Session session);

    @Mapping(target = "id", expression = "java(new SessionId(dto.getUser1Id(), dto.getUser2Id()))")
    @Mapping(target = "inviteCode", source = "inviteCode")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "lengthMin", source = "lengthMin")
    @Mapping(target = "lengthMax", source = "lengthMax")
    @Mapping(target = "difficulty", source = "difficulty")
    @Mapping(target = "biome", source = "biome")
    Session toEntity(SessionDto dto);
}
