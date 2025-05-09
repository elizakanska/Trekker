package com.trek.ker.entity.dto;

import com.trek.ker.entity.Trail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {
    private Long id;

    private Long userId;
    private Long trailId;

    private Trail trail;
}
