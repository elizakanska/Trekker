package com.trek.ker.controller;

import com.trek.ker.entity.User;
import com.trek.ker.entity.dto.UserDto;
import com.trek.ker.mapper.UserMapper;
import com.trek.ker.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

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

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        if (isValidToken(token)) {
            Optional<User> user = service.findByEmail(email);
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private boolean isValidToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("A3D53B73C46DBEF5FC8D8123FC9DF2CD6B85917D5BCA64D3DCC1DEE13D")
                    .parseClaimsJws(token)
                    .getBody();

            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                return false;
            }

            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (SignatureException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

