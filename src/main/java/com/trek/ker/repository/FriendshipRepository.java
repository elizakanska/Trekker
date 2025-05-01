package com.trek.ker.repository;

import com.trek.ker.entity.Friendship;
import com.trek.ker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUser1(User user);
    List<Friendship> findByUser2(User user);
}
