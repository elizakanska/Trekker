package com.trek.ker.repository;

import com.trek.ker.entity.SessionLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionLikeRepository extends JpaRepository<SessionLike, Long> {
    List<SessionLike> findBySession_Id(Long sessionId);

    List<SessionLike> findBySession_IdAndRound(Long sessionId, int round);
}
