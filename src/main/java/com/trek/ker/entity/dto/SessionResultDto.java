package com.trek.ker.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResultDto {
    private Long sessionUser1Id;
    private Long sessionUser2Id;
    private Long trailId;
    private Integer finalRank;
}