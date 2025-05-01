package com.trek.ker.service;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.User;
import com.trek.ker.entity.id.SessionId;
import com.trek.ker.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    private final Logger logger = LoggerFactory.getLogger(SessionService.class);
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getActiveSessions(User user) {
        List<Session> userSessions = new ArrayList<>();
        userSessions.addAll(sessionRepository.findByUser1(user));
        userSessions.addAll(sessionRepository.findByUser2(user));
        return userSessions;
    }

    public Optional<Session> getSessionByInviteCode(Long inviteCode) {
        return Optional.ofNullable(sessionRepository.findByInviteCode(inviteCode));
    }

    public boolean createSession(Session session) {
        try {
            sessionRepository.save(session);
            return true;
        } catch (Exception e) {
            logger.error("Failed to create session", e);
            return false;
        }
    }

    public Optional<Session> getSessionById(SessionId id) {
        return sessionRepository.findById(id);
    }

    public boolean deleteSession(SessionId id) {
        try {
            sessionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("Failed to delete session", e);
            return false;
        }
    }
}
