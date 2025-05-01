package com.trek.ker.service;

import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.id.FavoriteId;
import com.trek.ker.repository.FavoriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// FavoriteService.java
@Service
public class FavoriteService {
    private final Logger logger = LoggerFactory.getLogger(FavoriteService.class);
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public boolean addFavorite(Favorite favorite) {
        try {
            Long userId = favorite.getUser().getId();
            Long trailId = favorite.getTrail().getId();
            logger.info("Adding favorite trail_id: {} for user_id: {}", trailId, userId);

            favoriteRepository.save(favorite);
            return true;
        } catch (Exception e) {
            logger.error("Failed to add favorite", e);
            return false;
        }
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public void removeFavorite(FavoriteId id) {
        favoriteRepository.deleteById(id);
    }
}

