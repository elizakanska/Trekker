package com.trek.ker.service;

import com.trek.ker.entity.Friend;
import com.trek.ker.entity.User;
import com.trek.ker.entity.id.FriendId;
import com.trek.ker.repository.FriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FriendServiceTest {

    private FriendRepository friendRepository;
    private FriendService friendService;

    @BeforeEach
    void setUp() {
        friendRepository = mock(FriendRepository.class);
        friendService = new FriendService(friendRepository);
    }

    @Test
    void getFriendshipsForUser_ReturnsCombinedList() {
        Long userId = 1L;
        List<Friend> list1 = Arrays.asList(new Friend(), new Friend());
        List<Friend> list2 = Arrays.asList(new Friend());

        when(friendRepository.findByUser1Id(userId)).thenReturn(list1);
        when(friendRepository.findByFriendId(userId)).thenReturn(list2);

        List<Friend> result = friendService.getFriendshipsForUser(userId);

        assertEquals(3, result.size());
        verify(friendRepository).findByUser1Id(userId);
        verify(friendRepository).findByFriendId(userId);
    }

    @Test
    void addFriendship_DifferentUsers_SavesAndReturnsTrue() {
        User user1 = new User();
        user1.setId(1L);
        User friend = new User();
        friend.setId(2L);
        Friend friendship = new Friend();
        friendship.setUser1(user1);
        friendship.setFriend(friend);

        boolean result = friendService.addFriendship(friendship);

        assertTrue(result);
        verify(friendRepository).save(friendship);
    }

    @Test
    void addFriendship_SameUserIds_ReturnsFalse() {
        User user = new User();
        user.setId(1L);
        Friend friendship = new Friend();
        friendship.setUser1(user);
        friendship.setFriend(user);  // same user

        boolean result = friendService.addFriendship(friendship);

        assertFalse(result);
        verify(friendRepository, never()).save(any());
    }

    @Test
    void addFriendship_ExceptionThrown_ReturnsFalse() {
        Friend friendship = mock(Friend.class);
        when(friendship.getUser1()).thenThrow(new RuntimeException("Mocked error"));

        boolean result = friendService.addFriendship(friendship);

        assertFalse(result);
        verify(friendRepository, never()).save(any());
    }

    @Test
    void removeFriend_DeletesFriendship() {
        FriendId id = new FriendId(1L, 2L);

        doNothing().when(friendRepository).deleteById(id);

        friendService.removeFriend(id);

        verify(friendRepository).deleteById(id);
    }
}
