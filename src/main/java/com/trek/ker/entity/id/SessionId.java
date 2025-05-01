package com.trek.ker.entity.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class SessionId implements Serializable {
    private Long user1;
    private Long user2;
}
