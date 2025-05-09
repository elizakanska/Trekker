package com.trek.ker.entity;

import com.trek.ker.entity.id.SessionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sessions", schema = "trekker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "user1Id", column = @Column(name = "user1_id")),
            @AttributeOverride(name = "user2Id", column = @Column(name = "user2_id"))
    })
    private SessionId id;

    @MapsId("user1Id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user1_id", insertable = false, updatable = false)
    private User user1;

    @MapsId("user2Id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user2_id", insertable = false, updatable = false)
    private User user2;

    @Column(name = "invite_code")
    private Long inviteCode;
}

