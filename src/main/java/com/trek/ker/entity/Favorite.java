package com.trek.ker.entity;

import com.trek.ker.entity.id.FavoriteId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorites", schema = "trekker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
            @AttributeOverride(name = "trailId", column = @Column(name = "trail_id"))
    })
    private FavoriteId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @MapsId("trailId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trail_id", insertable = false, updatable = false)
    private Trail trail;
}


