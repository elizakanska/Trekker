package com.trek.ker.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrailDto {
    private Long id;

    private String name;
    private String location;
    private String type;
    private String biome;
    private String difficulty;

    private Float length;
}
