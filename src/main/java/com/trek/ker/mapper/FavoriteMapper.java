package com.trek.ker.mapper;

import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.dto.FavoriteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    FavoriteDto toDto(Favorite favorite);
    Favorite toEntity(FavoriteDto dto);
}
