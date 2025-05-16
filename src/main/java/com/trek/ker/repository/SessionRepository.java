package com.trek.ker.repository;

import com.trek.ker.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByInviteCode(String inviteCode);

    List<Session> findByUser1IdOrderByIdDesc(Long user1Id);

    Optional<Session> findTopByUser1IdOrUser2IdOrderByIdDesc(Long userId, Long userId1);
}
