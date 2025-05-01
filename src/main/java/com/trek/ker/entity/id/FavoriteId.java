package com.trek.ker.entity.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class FavoriteId implements Serializable {
    private Long user;
    private Long trail;
}
