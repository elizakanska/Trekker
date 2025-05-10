package com.trek.ker.controller;

import com.trek.ker.entity.dto.FriendDto;
import com.trek.ker.entity.id.FriendId;
import com.trek.ker.mapper.FriendMapper;
import com.trek.ker.service.FriendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {
    private final FriendService service;
    private final FriendMapper mapper;

    public FriendController(FriendService service, FriendMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/user/{userId}")
    public List<FriendDto> getAll(@PathVariable Long userId) {
        return service.getFriendshipsForUser(userId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @PostMapping
    public void addFriend(@RequestParam String username, @RequestParam Long userId) {
        service.addFriendship(username, userId);
    }

    @DeleteMapping
    public void delete(@RequestBody FriendDto dto) {
        service.removeFriend(new FriendId(dto.getUser1Id(), dto.getFriendId()));
    }
}
