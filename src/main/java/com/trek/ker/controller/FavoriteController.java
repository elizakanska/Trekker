package com.trek.ker.controller;

import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.dto.FavoriteDto;
import com.trek.ker.entity.id.FavoriteId;
import com.trek.ker.mapper.FavoriteMapper;
import com.trek.ker.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService service;
    private final FavoriteMapper mapper;

    public FavoriteController(FavoriteService service, FavoriteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<FavoriteDto> add(@RequestBody FavoriteDto dto) {
        Favorite favorite = mapper.toEntity(dto);
        boolean added = service.addFavorite(favorite);
        if (added) {
            return ResponseEntity.ok(mapper.toDto(favorite));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}")
    public List<FavoriteDto> getByUser(@PathVariable Long userId) {
        return service.getFavoritesByUser(userId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @DeleteMapping
    public void delete(@RequestBody FavoriteDto dto) {
        service.removeFavorite(new FavoriteId(dto.getUserId(), dto.getTrailId()));
    }

}

