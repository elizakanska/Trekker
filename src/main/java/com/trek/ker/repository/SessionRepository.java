package com.trek.ker.repository;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.id.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, SessionId> {
    Optional<Session> findByInviteCode(String inviteCode);
}
