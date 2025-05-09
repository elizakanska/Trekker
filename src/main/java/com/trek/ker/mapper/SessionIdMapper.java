package com.trek.ker.mapper;

import com.trek.ker.entity.id.SessionId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionIdMapper {
    default Long map(SessionId sessionId) {
        return sessionId != null ? sessionId.getUser2Id() : null;
    }
}
