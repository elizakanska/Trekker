package com.trek.ker.service;

import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.User;
import com.trek.ker.entity.Trail;
import com.trek.ker.entity.id.FavoriteId;
import com.trek.ker.repository.FavoriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FavoriteServiceTest {

    private FavoriteRepository favoriteRepository;
    private FavoriteService favoriteService;

    @BeforeEach
    void setUp() {
        favoriteRepository = mock(FavoriteRepository.class);
        favoriteService = new FavoriteService(favoriteRepository);
    }

    @Test
    void addFavorite_ValidFavorite_ReturnsTrue() {
        User user = new User();
        user.setId(1L);
        Trail trail = new Trail();
        trail.setId(10L);
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setTrail(trail);

        doNothing().when(favoriteRepository).save(favorite);

        boolean result = favoriteService.addFavorite(favorite);

        assertTrue(result);
        verify(favoriteRepository, times(1)).save(favorite);
    }

    @Test
    void addFavorite_ThrowsException_ReturnsFalse() {
        Favorite favorite = mock(Favorite.class);
        when(favorite.getUser()).thenThrow(new RuntimeException("Failure"));

        boolean result = favoriteService.addFavorite(favorite);

        assertFalse(result);
        verify(favoriteRepository, never()).save(any());
    }

    @Test
    void getFavoritesByUser_ReturnsFavoritesList() {
        Long userId = 1L;
        List<Favorite> mockFavorites = Arrays.asList(new Favorite(), new Favorite());

        when(favoriteRepository.findByUserId(userId)).thenReturn(mockFavorites);

        List<Favorite> result = favoriteService.getFavoritesByUser(userId);

        assertEquals(2, result.size());
        verify(favoriteRepository).findByUserId(userId);
    }

    @Test
    void removeFavorite_DeletesFavorite() {
        FavoriteId favoriteId = new FavoriteId(1L, 10L);

        doNothing().when(favoriteRepository).deleteById(favoriteId);

        favoriteService.removeFavorite(favoriteId);

        verify(favoriteRepository).deleteById(favoriteId);
    }
}
