package com.trek.ker.mapper;

import com.trek.ker.entity.Trail;
import com.trek.ker.entity.dto.TrailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrailMapper {
    TrailDto toDto(Trail trail);
    Trail toEntity(TrailDto dto);
}
