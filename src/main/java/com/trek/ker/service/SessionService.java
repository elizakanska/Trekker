package com.trek.ker.service;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.SessionLike;
import com.trek.ker.entity.SessionResult;
import com.trek.ker.entity.Trail;
import com.trek.ker.entity.User;
import com.trek.ker.entity.enums.SessionState;
import com.trek.ker.repository.SessionLikeRepository;
import com.trek.ker.repository.SessionRepository;
import com.trek.ker.repository.SessionResultRepository;
import com.trek.ker.repository.TrailRepository;
import com.trek.ker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SessionService {
    private final SessionRepository sessionRepo;
    private final SessionLikeRepository likeRepo;
    private final SessionResultRepository resultRepo;
    private final TrailRepository trailRepo;
    private final UserRepository userRepo;

    public SessionService(SessionRepository sessionRepo,
                          SessionLikeRepository likeRepo,
                          SessionResultRepository resultRepo,
                          TrailRepository trailRepo,
                          UserRepository userRepo) {
        this.sessionRepo = sessionRepo;
        this.likeRepo = likeRepo;
        this.resultRepo = resultRepo;
        this.trailRepo = trailRepo;
        this.userRepo = userRepo;
    }

    public Session createSession(Long user1Id) {
        String invite = UUID.randomUUID().toString().substring(0, 6);
        Session s = new Session();
        s.setUser1Id(user1Id);
        s.setInviteCode(invite);
        s.setState(SessionState.CREATED);
        return sessionRepo.save(s);
    }

    @Transactional
    public Session joinSession(String inviteCode, Long user2Id) {
        Session s = sessionRepo.findByInviteCode(inviteCode).orElseThrow();
        s.setUser2Id(user2Id);
        s.setState(SessionState.JOINED);
        return sessionRepo.save(s);
    }

    @Transactional
    public Session setFilters(Long id, Float min, Float max, String diff, String biome) {
        Session s = sessionRepo.findById(id).orElseThrow();
        s.setLengthMin(min);
        s.setLengthMax(max);
        s.setDifficulty(diff);
        s.setBiome(biome);
        s.setState(SessionState.FILTERED);
        return sessionRepo.save(s);
    }

    public List<Trail> beginRound(Long id) {
        Session s = sessionRepo.findById(id).orElseThrow();
        List<Trail> list = trailRepo.findByLengthBetweenAndDifficultyAndBiome(
                s.getLengthMin(), s.getLengthMax(), s.getDifficulty(), s.getBiome());
        Collections.shuffle(list);
        s.setState(SessionState.IN_PROGRESS);
        sessionRepo.save(s);
        return list;
    }

    @Transactional
    public SessionLike recordLike(Long sessionId, Long trailId, Long userId, boolean liked, int round) {
        Session s = sessionRepo.findById(sessionId).orElseThrow();
        User u = userRepo.findById(userId).orElseThrow();
        Trail t = trailRepo.findById(trailId).orElseThrow();
        SessionLike sl = new SessionLike();
        sl.setSession(s);
        sl.setTrail(t);
        sl.setUser(u);
        sl.setLiked(liked);
        sl.setRound(round);
        return likeRepo.save(sl);
    }

    public List<Trail> mutualLikes(Long sessionId) {
        Session s = sessionRepo.findById(sessionId).orElseThrow();
        List<SessionLike> likes = likeRepo.findBySessionId(sessionId);
        Set<Long> u1 = likes.stream()
                .filter(l -> l.getRound() == 1 && l.getUser().getId().equals(s.getUser1Id()) && l.getLiked())
                .map(l -> l.getTrail().getId())
                .collect(Collectors.toSet());
        Set<Long> u2 = likes.stream()
                .filter(l -> l.getRound() == 1 && l.getUser().getId().equals(s.getUser2Id()) && l.getLiked())
                .map(l -> l.getTrail().getId())
                .collect(Collectors.toSet());
        u1.retainAll(u2);
        resultRepo.deleteBySessionId(sessionId);
        List<SessionResult> results = u1.stream().map(tid -> {
            SessionResult sr = new SessionResult();
            sr.setSession(s);
            sr.setTrail(trailRepo.findById(tid).orElseThrow());
            sr.setFinalRank(0);
            return sr;
        }).collect(Collectors.toList());
        resultRepo.saveAll(results);
        s.setState(SessionState.RANKING);
        sessionRepo.save(s);
        return results.stream().map(SessionResult::getTrail).collect(Collectors.toList());
    }

    public List<SessionResult> rankingRound(Long sessionId, int round) {
        List<SessionLike> roundLikes = likeRepo.findBySessionIdAndRound(sessionId, round);
        List<SessionResult> results = resultRepo.findBySessionId(sessionId);
        roundLikes.stream()
                .filter(SessionLike::getLiked)
                .forEach(l ->
                        results.stream()
                                .filter(r -> r.getTrail().getId().equals(l.getTrail().getId()))
                                .findFirst()
                                .ifPresent(r -> r.setFinalRank(r.getFinalRank() + 1))
                );
        resultRepo.saveAll(results);
        return results;
    }

    public List<SessionResult> finalizeSession(Long sessionId) {
        List<SessionResult> results = resultRepo.findBySessionId(sessionId);
        results.sort(Comparator.comparingInt(SessionResult::getFinalRank).reversed());
        int keep = Math.max(1, results.size() / 2);
        List<SessionResult> top = new ArrayList<>(results.subList(0, keep));
        resultRepo.deleteBySessionIdAndTrailIdNotIn(
                sessionId,
                top.stream().map(r -> r.getTrail().getId()).collect(Collectors.toList())
        );
        Session s = sessionRepo.findById(sessionId).orElseThrow();
        s.setState(SessionState.COMPLETE);
        sessionRepo.save(s);
        return top;
    }
}
