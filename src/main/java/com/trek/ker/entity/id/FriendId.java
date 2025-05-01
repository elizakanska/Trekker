package com.trek.ker.entity.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class FriendId implements Serializable {
    private Long user1;
    private Long friend;
}
