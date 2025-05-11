package com.trek.ker.entity.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResultDto {
    private Long id;
    private Long sessionId;
    private Long trailId;
    private Integer finalRank;
}
