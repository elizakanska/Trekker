package com.trek.ker.repository;

import com.trek.ker.entity.SessionLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionLikeRepository extends JpaRepository<SessionLike, Long> {
    List<SessionLike> findBySessionId(Long sessionId);

    List<SessionLike> findBySessionIdAndRound(Long sessionId, int round);
}
