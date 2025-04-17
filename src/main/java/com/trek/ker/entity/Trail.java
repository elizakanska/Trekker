package com.trek.ker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Trail {
    @Id
    private Long id;

    private String name;
    private String location;
    private String type;
    private String biome;
    private String difficulty;

    private Float length;
}
