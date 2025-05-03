package com.trek.ker.security;

import com.trek.ker.entity.dto.UserDto;
import com.trek.ker.mapper.UserMapper;
import com.trek.ker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper mapper;

    public AuthController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody UserDto userDto) {
        return mapper.toDto(userService.saveUser(mapper.toEntity(userDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }
}