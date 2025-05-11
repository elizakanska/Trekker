package com.trek.ker.repository;

import com.trek.ker.entity.SessionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionResultRepository extends JpaRepository<SessionResult, Long> {
    List<SessionResult> findBySessionId(Long sessionId);

    void deleteBySessionId(Long sessionId);

    void deleteBySessionIdAndTrailIdNotIn(Long sessionId, List<Long> trailIds);
}
