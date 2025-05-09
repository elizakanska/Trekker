package com.trek.ker.mapper;

import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.dto.FavoriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { FavoriteIdMapper.class })
public interface FavoriteMapper {

    @Mapping(target = "userId", source = "id.userId")
    @Mapping(target = "trailId", source = "id.trailId")
    @Mapping(target = "trail", source = "trail")
    FavoriteDto toDto(Favorite favorite);

    @Mapping(target = "id", expression = "java(new FavoriteId(dto.getUserId(), dto.getTrailId()))")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "trail.id", source = "trailId")
    Favorite toEntity(FavoriteDto dto);
}

