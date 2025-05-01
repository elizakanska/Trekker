package com.trek.ker.repository;

import com.trek.ker.entity.Trail;
import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.User;
import com.trek.ker.entity.id.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByTrail(Trail trail);
    
    List<Favorite> findByUserId(Long userId);

    void deleteById(FavoriteId id);
}
