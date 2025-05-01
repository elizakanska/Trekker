package com.trek.ker.repository;

import com.trek.ker.entity.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Long> {
    Trail findByName(String name);
    List<Trail> findByLocation(String location);
    List<Trail> findByType(String type);
    List<Trail> findByBiome(String biome);
    List<Trail> findByDifficulty(String difficulty);
    List<Trail> findByLength(Float length);
}
