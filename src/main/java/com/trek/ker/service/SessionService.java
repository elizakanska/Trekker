package com.trek.ker.service;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.id.SessionId;
import com.trek.ker.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepo;

    public List<Session> getAllSessions() {
        return sessionRepo.findAll();
    }

    public Session createSession(Session session) {
        return sessionRepo.save(session);
    }

    public void deleteSession(SessionId id) {
        sessionRepo.deleteById(id);
    }
}
