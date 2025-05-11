package com.trek.ker.entity.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionLikeDto {
    private Long id;
    private Long sessionId;
    private Long trailId;
    private Long userId;
    private Boolean liked;
    private Integer round;
}
