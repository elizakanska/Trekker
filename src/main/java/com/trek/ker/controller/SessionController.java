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

    @GetMapping("/current")
    public Session getCurrent(@RequestParam Long userId) {
        return service.getCurrentSession(userId);
    }

    @GetMapping("/{user1Id}/users")
    public List<String> getUsers(@PathVariable Long user1Id) {
        return service.getSessionUsernames(user1Id);
    }

    @PatchMapping("/{user1Id}/filters")
    public Session setFilters(
            @PathVariable Long user1Id,
            @RequestParam Float min,
            @RequestParam Float max,
            @RequestParam String difficulty,
            @RequestParam String biome
    ) {
        return service.setFilters(user1Id, min, max, difficulty, biome);
    }

    @GetMapping("/{user1Id}/trails")
    public List<Trail> begin(@PathVariable Long user1Id) {
        return service.beginRound(user1Id);
    }

    @PostMapping("/{user1Id}/likes")
    public SessionLike like(
            @PathVariable Long user1Id,
            @RequestParam Long trailId,
            @RequestParam Long userId,
            @RequestParam boolean liked,
            @RequestParam int round
    ) {
        return service.recordLike(user1Id, trailId, userId, liked, round);
    }

    @GetMapping("/{user1Id}/mutual")
    public List<Trail> mutual(@PathVariable Long user1Id) {
        return service.mutualLikes(user1Id);
    }

    @PostMapping("/{user1Id}/rank")
    public List<SessionResult> rank(
            @PathVariable Long user1Id,
            @RequestParam int round
    ) {
        return service.rankingRound(user1Id, round);
    }

    @GetMapping("/{user1Id}/final")
    public List<SessionResult> finish(@PathVariable Long user1Id) {
        return service.finalizeSession(user1Id);
    }
}
