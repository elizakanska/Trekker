package com.trek.ker.entity;

import com.trek.ker.entity.enums.SessionState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sessions", schema = "trekker")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user1_id", nullable = false)
    private Long user1Id;

    @Column(name = "user2_id")
    private Long user2Id;

    @Column(name = "invite_code", nullable = false, unique = true)
    private String inviteCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private SessionState state;

    @Column(name = "length_min")
    private Float lengthMin;

    @Column(name = "length_max")
    private Float lengthMax;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "biome")
    private String biome;
}
