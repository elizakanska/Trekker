package com.trek.ker.controller;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.User;
import com.trek.ker.entity.dto.SessionDto;
import com.trek.ker.entity.id.SessionId;
import com.trek.ker.mapper.SessionMapper;
import com.trek.ker.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final SessionMapper sessionMapper;

    public SessionController(SessionService sessionService, SessionMapper sessionMapper) {
        this.sessionService = sessionService;
        this.sessionMapper = sessionMapper;
    }

    @GetMapping("/user/{userId}")
    public List<SessionDto> getActiveSessions(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return sessionService.getActiveSessions(user).stream()
                .map(sessionMapper::toDto)
                .toList();
    }

    @GetMapping("/invite/{inviteCode}")
    public ResponseEntity<SessionDto> getSessionByInviteCode(@PathVariable Long inviteCode) {
        Optional<Session> session = sessionService.getSessionByInviteCode(inviteCode);
        return session.map(s -> ResponseEntity.ok(sessionMapper.toDto(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SessionDto> create(@RequestBody SessionDto dto) {
        Session session = sessionMapper.toEntity(dto);
        boolean created = sessionService.createSession(session);
        if (created) return ResponseEntity.ok(sessionMapper.toDto(session));
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{user1Id}/{user2Id}")
    public ResponseEntity<SessionDto> getById(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        SessionId id = new SessionId(user1Id, user2Id);
        return sessionService.getSessionById(id)
                .map(sessionMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody SessionDto dto) {
        SessionId id = new SessionId(dto.getUser1Id(), dto.getUser2Id());
        boolean deleted = sessionService.deleteSession(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
