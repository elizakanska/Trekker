package com.trek.ker.entity;

import com.trek.ker.entity.id.FavoriteId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "favorites")
@IdClass(FavoriteId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "trail_id")
    private Trail trail;
}

