package com.trek.ker.service;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.SessionLike;
import com.trek.ker.entity.SessionResult;
import com.trek.ker.entity.Trail;
import com.trek.ker.entity.User;
import com.trek.ker.entity.enums.SessionState;
import com.trek.ker.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
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

    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom rnd = new SecureRandom();

    public Session createSession(Long user1Id) {
        String invite = randomCode();
        Session s = new Session();
        s.setUser1Id(user1Id);
        s.setInviteCode(invite);
        s.setState(SessionState.CREATED);
        return sessionRepo.save(s);
    }

    private String randomCode() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHANUM.charAt(rnd.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }

    @Transactional
    public Session joinSession(String inviteCode, Long user2Id) {
        Session s = sessionRepo.findByInviteCode(inviteCode).orElseThrow();
        s.setUser2Id(user2Id);
        s.setState(SessionState.JOINED);
        return sessionRepo.save(s);
    }

    public Session getCurrentSession(Long userId) {
        return sessionRepo.findTopByUser1IdOrUser2IdOrderByIdDesc(userId, userId)
                .orElseThrow(() -> new NoSuchElementException("No session for user " + userId));
    }


    public List<String> getSessionUsernames(Long user1Id) {
        List<Session> sessions = sessionRepo.findByUser1IdOrderByIdDesc(user1Id);
        if (sessions.isEmpty()) throw new NoSuchElementException("No session for user " + user1Id);
        Session s = sessions.get(0);
        User u1 = userRepo.findById(s.getUser1Id()).orElseThrow();
        List<String> names = new ArrayList<>();
        names.add(u1.getUsername());
        if (s.getUser2Id() != null) {
            User u2 = userRepo.findById(s.getUser2Id()).orElseThrow();
            names.add(u2.getUsername());
        }
        return names;
    }

    @Transactional
    public Session setFilters(Long user1Id, Float min, Float max, String difficulty, String biome) {
        List<Session> sessions = sessionRepo.findByUser1IdOrderByIdDesc(user1Id);
        Session s = sessions.isEmpty() ? null : sessions.get(0);
        if (s == null) throw new NoSuchElementException("No session for user " + user1Id);
        s.setLengthMin(min);
        s.setLengthMax(max);
        s.setDifficulty(difficulty);
        s.setBiome(biome);
        s.setState(SessionState.FILTERED);
        return sessionRepo.save(s);
    }

    public List<Trail> beginRound(Long user1Id) {
        List<Session> sessions = sessionRepo.findByUser1IdOrderByIdDesc(user1Id);
        Session s = sessions.isEmpty() ? null : sessions.get(0);
        if (s == null) throw new NoSuchElementException("No session for user " + user1Id);

        List<Trail> list = trailRepo.findByLengthBetweenAndDifficultyAndBiomeContainingIgnoreCase(
                s.getLengthMin(),
                s.getLengthMax(),
                s.getDifficulty(),
                s.getBiome());

        Collections.shuffle(list);
        s.setState(SessionState.IN_PROGRESS);
        sessionRepo.save(s);
        return list;
    }

    @Transactional
    public SessionLike recordLike(Long user1Id, Long trailId, Long userId, boolean liked, int round) {
        List<Session> sessions = sessionRepo.findByUser1IdOrderByIdDesc(user1Id);
        Session s = sessions.isEmpty() ? null : sessions.get(0);
        if (s == null) throw new NoSuchElementException("No session for user " + user1Id);
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

    public List<Trail> mutualLikes(Long user1Id) {
        List<Session> sessions = sessionRepo.findByUser1IdOrderByIdDesc(user1Id);
        Session s = sessions.isEmpty() ? null : sessions.get(0);
        if (s == null) throw new NoSuchElementException("No session for user " + user1Id);
        List<SessionLike> likes = likeRepo.findBySession_Id(s.getId());
        Set<Long> u1 = likes.stream()
                .filter(l -> l.getUser().getId().equals(s.getUser1Id()) && l.getRound() == 1 && l.getLiked())
                .map(l -> l.getTrail().getId())
                .collect(Collectors.toSet());
        Set<Long> u2 = likes.stream()
                .filter(l -> l.getUser().getId().equals(s.getUser2Id()) && l.getRound() == 1 && l.getLiked())
                .map(l -> l.getTrail().getId())
                .collect(Collectors.toSet());
        u1.retainAll(u2);
        resultRepo.deleteBySession_Id(s.getId());
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
        return results.stream().map(SessionResult::getTrail).toList();
    }

    public List<SessionResult> rankingRound(Long user1Id, int round) {
        List<Session> sessions = sessionRepo.findByUser1IdOrderByIdDesc(user1Id);
        Session s = sessions.isEmpty() ? null : sessions.get(0);
        if (s == null) throw new NoSuchElementException("No session for user " + user1Id);
        List<SessionLike> roundLikes = likeRepo.findBySession_IdAndRound(s.getId(), round);
        List<SessionResult> results = resultRepo.findBySession_Id(s.getId());
        roundLikes.stream()
                .filter(SessionLike::getLiked)
                .forEach(l -> results.stream()
                        .filter(r -> r.getTrail().getId().equals(l.getTrail().getId()))
                        .findFirst()
                        .ifPresent(r -> r.setFinalRank(r.getFinalRank() + 1)));
        resultRepo.saveAll(results);
        return results;
    }

    public List<SessionResult> finalizeSession(Long user1Id) {
        List<Session> sessions = sessionRepo.findByUser1IdOrderByIdDesc(user1Id);
        Session s = sessions.isEmpty() ? null : sessions.get(0);
        if (s == null) throw new NoSuchElementException("No session for user " + user1Id);
        List<SessionResult> results = resultRepo.findBySession_Id(s.getId());
        results.sort(Comparator.comparingInt(SessionResult::getFinalRank).reversed());
        int keep = Math.max(1, results.size() / 2);
        List<SessionResult> top = new ArrayList<>(results.subList(0, keep));
        resultRepo.deleteBySession_IdAndTrailIdNotIn(s.getId(), top.stream()
                .map(r -> r.getTrail().getId()).toList());
        s.setState(SessionState.COMPLETE);
        sessionRepo.save(s);
        return top;
    }
}
