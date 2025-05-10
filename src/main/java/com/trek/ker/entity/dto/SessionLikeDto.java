package com.trek.ker.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionLikeDto {
    private Long sessionUser1Id;
    private Long sessionUser2Id;
    private Long trailId;
    private Long userId;
    private Boolean liked;
    private Integer round;
}