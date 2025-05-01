package com.trek.ker.repository;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.User;
import com.trek.ker.entity.id.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByUser1(User user);
    List<Friend> findByFriend(User user);

    void deleteById(FriendId id);
}
