package com.trek.ker.service;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.id.FriendId;
import com.trek.ker.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepo;

    public List<Friend> getAllFriends() {
        return friendRepo.findAll();
    }

    public Friend addFriend(Friend friend) {
        return friendRepo.save(friend);
    }

    public void removeFriend(FriendId id) {
        friendRepo.deleteById(id);
    }
}
