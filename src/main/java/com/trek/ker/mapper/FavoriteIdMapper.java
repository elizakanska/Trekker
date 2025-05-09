package com.trek.ker.mapper;

import com.trek.ker.entity.id.FavoriteId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavoriteIdMapper {
    default Long map(FavoriteId favoriteId) {
        return favoriteId != null ? favoriteId.getTrailId() : null;
    }
}
