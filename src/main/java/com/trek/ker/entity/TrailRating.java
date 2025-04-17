package com.trek.ker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TrailRating {

    @Id
    private Long id;

    private Long userId;
    private Long trailId;
    private Long rating;
}
