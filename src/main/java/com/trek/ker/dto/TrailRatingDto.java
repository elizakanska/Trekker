package com.trek.ker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrailRatingDto {
    private Long id;

    private Long userId;
    private Long trailId;
    private Long rating;
}
