package com.trek.ker.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trail_id", nullable = false)
    private Trail trail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Boolean liked;

    @Column(nullable = false)
    private Integer round;
}
