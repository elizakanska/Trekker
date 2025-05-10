package com.trek.ker.repository;

import com.trek.ker.entity.SessionLike;
import com.trek.ker.entity.id.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionLikeRepository extends JpaRepository<SessionLike, Long> {
    List<SessionLike> findBySessionId(SessionId sessionId);
    List<SessionLike> findBySessionIdAndRound(SessionId sessionId, int round);
}
