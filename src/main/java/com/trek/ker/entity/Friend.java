package com.trek.ker.entity;

import com.trek.ker.entity.id.FriendId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friends")
@IdClass(FriendId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    @Id
    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @Id
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;
}

