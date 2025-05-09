package com.trek.ker.entity;

import com.trek.ker.entity.id.FriendId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "friends", schema = "trekker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "user1Id", column = @Column(name = "user1_id")),
            @AttributeOverride(name = "friendId", column = @Column(name = "friend_id"))
    })
    private FriendId id;

    @MapsId("user1Id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user1_id", insertable = false, updatable = false)
    private User user1;

    @MapsId("friendId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id", insertable = false, updatable = false)
    private User friend;
}

