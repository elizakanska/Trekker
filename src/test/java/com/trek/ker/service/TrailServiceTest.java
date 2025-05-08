package com.trek.ker.service;

import com.trek.ker.entity.Trail;
import com.trek.ker.repository.TrailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrailServiceTest {

    @Mock
    private TrailRepository trailRepository;

    @InjectMocks
    private TrailService trailService;

    private Trail trail1;
    private Trail trail2;

    @BeforeEach
    void setUp() {
        trail1 = new Trail();
        trail1.setId(1L);
        trail1.setName("Mountain Trail");
        trail1.setDifficulty("Medium");

        trail2 = new Trail();
        trail2.setId(2L);
        trail2.setName("Forest Path");
        trail2.setDifficulty("Easy");
    }

    @Test
    void getAllTrails_ShouldReturnAllTrails() {
        when(trailRepository.findAll()).thenReturn(Arrays.asList(trail1, trail2));

        List<Trail> trails = trailService.getAllTrails();

        assertEquals(2, trails.size());
        verify(trailRepository, times(1)).findAll();
    }

    @Test
    void getTrailById_WithValidId_ShouldReturnTrail() {
        when(trailRepository.findById(1L)).thenReturn(Optional.of(trail1));

        Trail foundTrail = trailService.getTrailById(1L);

        assertNotNull(foundTrail);
        assertEquals("Mountain Trail", foundTrail.getName());
        verify(trailRepository, times(1)).findById(1L);
    }

    @Test
    void getTrailById_WithInvalidId_ShouldReturnNull() {
        when(trailRepository.findById(99L)).thenReturn(Optional.empty());

        Trail foundTrail = trailService.getTrailById(99L);

        assertNull(foundTrail);
        verify(trailRepository, times(1)).findById(99L);
    }

    @Test
    void saveTrail_ShouldReturnSavedTrail() {
        Trail newTrail = new Trail();
        newTrail.setName("Coastal Walk");

        when(trailRepository.save(newTrail)).thenAnswer(invocation -> {
            Trail t = invocation.getArgument(0);
            t.setId(3L);
            return t;
        });

        Trail savedTrail = trailService.saveTrail(newTrail);

        assertNotNull(savedTrail.getId());
        assertEquals("Coastal Walk", savedTrail.getName());
        verify(trailRepository, times(1)).save(newTrail);
    }

    @Test
    void deleteTrail_ShouldCallDeleteMethod() {

        trailService.deleteTrail(1L);

        verify(trailRepository, times(1)).deleteById(1L);
    }

    @Test
    void saveTrail_WithNullTrail_ShouldThrowException() {
        when(trailRepository.save(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            trailService.saveTrail(null);
        });
    }

    @Test
    void getAllTrails_WhenNoTrailsExist_ShouldReturnEmptyList() {
        when(trailRepository.findAll()).thenReturn(List.of());

        List<Trail> trails = trailService.getAllTrails();

        assertTrue(trails.isEmpty());
        verify(trailRepository, times(1)).findAll();
    }
}