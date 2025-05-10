package com.trek.ker.service;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.id.FriendId;
import com.trek.ker.entity.User;
import com.trek.ker.repository.FriendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FriendService {
    private static final Logger logger = LoggerFactory.getLogger(FriendService.class);

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Autowired
    public FriendService(FriendRepository friendRepository, UserService userService) {
        this.friendRepository = friendRepository;
        this.userService = userService;
    }

    public List<Friend> getFriendshipsForUser(Long userId) {
        List<Friend> byUser1 = friendRepository.findByUser1Id(userId);
        List<Friend> byFriend = friendRepository.findByFriendId(userId);
        byUser1.addAll(byFriend);
        return byUser1;
    }

    public boolean addFriendship(String username, Long userId) {
        Optional<User> friend = userService.findByUsername(username);
        if (friend.isPresent()) {
            Long friendId = friend.get().getId();
            if (!Objects.equals(userId, friendId)) {
                logger.info("Creating friendship: user1_id = {}, friend_id = {}", userId, friendId);
                Friend friendship = new Friend(new FriendId(userId, friendId));
                friendRepository.save(friendship);
                return true;
            } else {
                logger.warn("Cannot friend oneself");
                return false;
            }
        }
        logger.warn("User not found with username: {}", username);
        return false;
    }

    public void removeFriend(FriendId id) {
        friendRepository.deleteById(id);
    }
}
