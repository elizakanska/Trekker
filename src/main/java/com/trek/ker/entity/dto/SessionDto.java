package com.trek.ker.entity.dto;

import com.trek.ker.entity.enums.SessionState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    private Long user1Id;
    private Long user2Id;
    private String inviteCode;
    private SessionState state;
    private Float lengthMin;
    private Float lengthMax;
    private String difficulty;
    private String biome;
}