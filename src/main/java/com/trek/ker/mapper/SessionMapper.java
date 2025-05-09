package com.trek.ker.mapper;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.dto.SessionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SessionIdMapper.class})
public interface SessionMapper {
    @Mapping(target = "user1Id", source = "id.user1Id")
    @Mapping(target = "user2Id", source = "id.user2Id")
    @Mapping(target = "inviteCode", source = "inviteCode")
    SessionDto toDto(Session session);

    @Mapping(target = "id", expression = "java(new SessionId(dto.getUser1Id(), dto.getUser2Id()))")
    @Mapping(target = "user1.id", source = "user1Id")
    @Mapping(target = "user2.id", source = "user2Id")
    @Mapping(target = "inviteCode", source = "inviteCode")
    Session toEntity(SessionDto dto);
}

