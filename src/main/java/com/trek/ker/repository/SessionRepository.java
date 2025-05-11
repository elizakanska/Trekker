package com.trek.ker.repository;

import com.trek.ker.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByInviteCode(String inviteCode);
}
