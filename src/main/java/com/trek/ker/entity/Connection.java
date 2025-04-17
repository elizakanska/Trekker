package com.trek.ker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//Friendship connection
public class Connection {
    @Id
    private Long id;

    private Long user1Id;
    private Long user2Id;
}
