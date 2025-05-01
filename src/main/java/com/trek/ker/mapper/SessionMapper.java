package com.trek.ker.mapper;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.dto.SessionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionDto toDto(Session session);
    Session toEntity(SessionDto dto);
}
