package com.trek.ker.controller;

import com.trek.ker.entity.dto.SessionDto;
import com.trek.ker.entity.id.SessionId;
import com.trek.ker.mapper.SessionMapper;
import com.trek.ker.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService service;
    private final SessionMapper mapper;

    public SessionController(SessionService service, SessionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<SessionDto> getAll() {
        return service.getAllSessions().stream().map(mapper::toDto).toList();
    }

    @PostMapping
    public SessionDto create(@RequestBody SessionDto dto) {
        return mapper.toDto(service.createSession(mapper.toEntity(dto)));
    }

    @DeleteMapping
    public void delete(@RequestBody SessionDto dto) {
        service.deleteSession(new SessionId(dto.getUser1Id(), dto.getUser2Id()));
    }
}
