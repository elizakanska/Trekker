package com.trek.ker.controller;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.SessionLike;
import com.trek.ker.entity.SessionResult;
import com.trek.ker.entity.Trail;
import com.trek.ker.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @PostMapping
    public Session create(@RequestParam Long user1Id) {
        return service.createSession(user1Id);
    }

    @PostMapping("/{invite}/join")
    public Session join(@PathVariable String invite, @RequestParam Long user2Id) {
        return service.joinSession(invite, user2Id);
    }

    @PatchMapping("/{sessionId}/filters")
    public Session setFilters(
            @PathVariable Long sessionId,
            @RequestParam Float min,
            @RequestParam Float max,
            @RequestParam String difficulty,
            @RequestParam String biome
    ) {
        return service.setFilters(sessionId, min, max, difficulty, biome);
    }

    @GetMapping("/{sessionId}/trails")
    public List<Trail> begin(
            @PathVariable Long sessionId
    ) {
        return service.beginRound(sessionId);
    }

    @PostMapping("/{sessionId}/likes")
    public SessionLike like(
            @PathVariable Long sessionId,
            @RequestParam Long trailId,
            @RequestParam Long userId,
            @RequestParam boolean liked,
            @RequestParam int round
    ) {
        return service.recordLike(sessionId, trailId, userId, liked, round);
    }

    @GetMapping("/{sessionId}/mutual")
    public List<Trail> mutual(
            @PathVariable Long sessionId
    ) {
        return service.mutualLikes(sessionId);
    }

    @PostMapping("/{sessionId}/rank")
    public List<SessionResult> rank(
            @PathVariable Long sessionId,
            @RequestParam int round
    ) {
        return service.rankingRound(sessionId, round);
    }

    @GetMapping("/{sessionId}/final")
    public List<SessionResult> finish(
            @PathVariable Long sessionId
    ) {
        return service.finalizeSession(sessionId);
    }
}
