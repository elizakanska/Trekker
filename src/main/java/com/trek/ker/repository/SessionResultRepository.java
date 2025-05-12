package com.trek.ker.repository;

import com.trek.ker.entity.SessionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionResultRepository extends JpaRepository<SessionResult, Long> {
    List<SessionResult> findBySession_Id(Long sessionId);

    void deleteBySession_Id(Long sessionId);

    void deleteBySession_IdAndTrailIdNotIn(Long sessionId, List<Long> trailIds);
}
