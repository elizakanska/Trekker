package com.trek.ker.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDto {
    private Long id;

    private Long user1Id;
    private Long user2Id;
}
