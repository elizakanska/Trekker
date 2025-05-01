package com.trek.ker.controller;

import com.trek.ker.entity.dto.TrailDto;
import com.trek.ker.mapper.TrailMapper;
import com.trek.ker.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trails")
public class TrailController {
    @Autowired private TrailService service;
    @Autowired private TrailMapper mapper;

    @GetMapping
    public List<TrailDto> getAll() {
        return service.getAllTrails().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public TrailDto get(@PathVariable Long id) {
        return mapper.toDto(service.getTrailById(id));
    }

    @PostMapping
    public TrailDto create(@RequestBody TrailDto dto) {
        return mapper.toDto(service.saveTrail(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTrail(id);
    }
}
