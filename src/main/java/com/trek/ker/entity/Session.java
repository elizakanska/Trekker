package com.trek.ker.entity;

import com.trek.ker.entity.id.SessionId;
import com.trek.ker.entity.enums.SessionState;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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
    private SessionId id;
    @Column(name = "invite_code", unique = true, nullable = false)
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
