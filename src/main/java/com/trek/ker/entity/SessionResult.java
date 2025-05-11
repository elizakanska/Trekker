package com.trek.ker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "session_results", schema = "trekker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trail_id", nullable = false)
    private Trail trail;

    @Column(name = "final_rank", nullable = false)
    private Integer finalRank;
}
