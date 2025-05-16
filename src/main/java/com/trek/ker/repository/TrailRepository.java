package com.trek.ker.repository;

import com.trek.ker.entity.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Long> {

    @Query("SELECT t FROM Trail t WHERE " +
            "t.length BETWEEN :length AND :length2 AND " +
            "t.difficulty = :difficulty AND " +
            "LOWER(t.biome) LIKE LOWER(CONCAT('%', :biome, '%'))")
    List<Trail> findByLengthBetweenAndDifficultyAndBiomeContainingIgnoreCase(
            @Param("length") Float length,
            @Param("length2") Float length2,
            @Param("difficulty") String difficulty,
            @Param("biome") String biome);
}