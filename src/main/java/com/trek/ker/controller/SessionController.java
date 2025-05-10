package com.trek.ker.controller;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.SessionLike;
import com.trek.ker.entity.SessionResult;
import com.trek.ker.entity.Trail;
import com.trek.ker.entity.id.SessionId;
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

    @PatchMapping("/{user1Id}/{user2Id}/filters")
    public Session filters(@PathVariable Long user1Id, @PathVariable Long user2Id,
                           @RequestParam Float min, @RequestParam Float max,
                           @RequestParam String difficulty, @RequestParam String biome) {
        return service.setFilters(new SessionId(user1Id, user2Id), min, max, difficulty, biome);
    }

    @GetMapping("/{user1Id}/{user2Id}/trails")
    public List<Trail> begin(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        return service.beginRound(new SessionId(user1Id, user2Id));
    }

    @PostMapping("/{user1Id}/{user2Id}/likes")
    public SessionLike like(@PathVariable Long user1Id, @PathVariable Long user2Id,
                            @RequestParam Long trailId, @RequestParam Long userId,
                            @RequestParam boolean liked, @RequestParam int round) {
        return service.recordLike(new SessionId(user1Id, user2Id), trailId, userId, liked, round);
    }

    @GetMapping("/{user1Id}/{user2Id}/mutual")
    public List<Trail> mutual(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        return service.mutualLikes(new SessionId(user1Id, user2Id));
    }

    @PostMapping("/{user1Id}/{user2Id}/rank")
    public List<SessionResult> rank(@PathVariable Long user1Id, @PathVariable Long user2Id,
                                    @RequestParam int round) {
        return service.rankingRound(new SessionId(user1Id, user2Id), round);
    }

    @GetMapping("/{user1Id}/{user2Id}/final")
    public List<SessionResult> finish(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        return service.finalizeSession(new SessionId(user1Id, user2Id));
    }
}
