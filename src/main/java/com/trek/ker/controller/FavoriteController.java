package com.trek.ker.controller;

import com.trek.ker.entity.dto.FavoriteDto;
import com.trek.ker.entity.id.FavoriteId;
import com.trek.ker.mapper.FavoriteMapper;
import com.trek.ker.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired private FavoriteService service;
    @Autowired private FavoriteMapper mapper;

    @GetMapping("/user/{userId}")
    public List<FavoriteDto> getUserFavorites(@PathVariable Long userId) {
        return service.getFavoritesByUser(userId).stream().map(mapper::toDto).toList();
    }

    @PostMapping
    public FavoriteDto add(@RequestBody FavoriteDto dto) {
        return mapper.toDto(service.addFavorite(mapper.toEntity(dto)));
    }

    @DeleteMapping
    public void delete(@RequestBody FavoriteDto dto) {
        service.removeFavorite(new FavoriteId(dto.getUserId(), dto.getTrailId()));
    }
}
