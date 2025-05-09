package com.trek.ker.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FriendId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "user1_id", nullable = false)
    private Long user1Id;

    @Column(name = "friend_id", nullable = false)
    private Long friendId;
}

