package com.trek.ker.controller;

import com.trek.ker.entity.dto.UserDto;
import com.trek.ker.mapper.UserMapper;
import com.trek.ker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired private UserMapper mapper;

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAllUsers().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return mapper.toDto(service.getUserById(id));
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return mapper.toDto(service.saveUser(mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}

