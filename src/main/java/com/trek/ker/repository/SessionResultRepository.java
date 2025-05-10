package com.trek.ker.repository;

import com.trek.ker.entity.SessionResult;
import com.trek.ker.entity.id.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionResultRepository extends JpaRepository<SessionResult, Long> {
    List<SessionResult> findBySessionId(SessionId sessionId);
    void deleteBySessionId(SessionId sessionId);
    void deleteBySessionIdAndTrailIdNotIn(SessionId sessionId, List<Long> trailIds);
}
