package com.trek.ker.repository;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.id.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    List<Friend> findByUser1Id(Long userId);

    List<Friend> findByFriendId(Long userId);
}
