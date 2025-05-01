package com.trek.ker.service;

import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.id.FavoriteId;
import com.trek.ker.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepo;

    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepo.findByUserId(userId);
    }

    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepo.save(favorite);
    }

    public void removeFavorite(FavoriteId id) {
        favoriteRepo.deleteById(id);
    }
}
