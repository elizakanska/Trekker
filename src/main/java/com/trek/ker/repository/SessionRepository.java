package com.trek.ker.repository;

import com.trek.ker.entity.Session;
import com.trek.ker.entity.User;
import com.trek.ker.entity.id.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SessionRepository extends JpaRepository<Session, SessionId> {
    List<Session> findByUser1(User user);
    List<Session> findByUser2(User user);
    Session findByInviteCode(Long inviteCode);
}
