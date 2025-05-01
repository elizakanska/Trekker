package com.trek.ker.entity;

import com.trek.ker.entity.id.SessionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sessions")
@IdClass(SessionId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @Id
    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    private Long inviteCode;
}

