package com.trek.ker.entity;

import com.trek.ker.entity.id.SessionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session_likes", schema = "trekker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SessionId sessionId;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "session_user1_id", referencedColumnName = "user1_id", insertable = false, updatable = false),
            @JoinColumn(name = "session_user2_id", referencedColumnName = "user2_id", insertable = false, updatable = false)
    })
    private Session session;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trail_id", referencedColumnName = "id", nullable = false)
    private Trail trail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "liked", nullable = false)
    private Boolean liked;

    @Column(name = "round", nullable = false)
    private Integer round;

}
