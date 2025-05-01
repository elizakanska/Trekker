package com.trek.ker.service;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.id.FriendId;
import com.trek.ker.repository.FriendRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FriendService {
    private static final Logger logger = LoggerFactory.getLogger(FriendService.class);

    private final FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> getFriendshipsForUser(Long userId) {
        List<Friend> byUser1 = friendRepository.findByUser1Id(userId);
        List<Friend> byFriend = friendRepository.findByFriendId(userId);
        byUser1.addAll(byFriend);
        return byUser1;
    }

    public boolean addFriendship(Friend friendship) {
        try {
            Long user1Id = friendship.getUser1().getId();
            Long friendId = friendship.getFriend().getId();
            logger.info("Creating friendship: user1_id = {}, friend_id = {}", user1Id, friendId);

            if (!Objects.equals(user1Id, friendId)) {
                logger.info("IDs are different, saving friendship");
                friendRepository.save(friendship);
                return true;
            } else {
                logger.warn("Cannot friend oneself");
                return false;
            }
        } catch (Exception e) {
            logger.error("Error saving friendship", e);
            return false;
        }
    }

    public void removeFriend(FriendId id) {
        friendRepository.deleteById(id);
    }
}

