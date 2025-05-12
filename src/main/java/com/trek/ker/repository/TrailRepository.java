package com.trek.ker.repository;

import com.trek.ker.entity.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Long> {
    List<Trail> findByLengthBetweenAndDifficultyAndBiome(Float length, Float length2, String difficulty, String biome);
}

