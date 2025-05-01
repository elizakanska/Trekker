package com.trek.ker.controller;

import com.trek.ker.entity.dto.FriendDto;
import com.trek.ker.entity.id.FriendId;
import com.trek.ker.mapper.FriendMapper;
import com.trek.ker.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired private FriendService service;
    @Autowired private FriendMapper mapper;

    @GetMapping
    public List<FriendDto> getAll() {
        return service.getAllFriends().stream().map(mapper::toDto).toList();
    }

    @PostMapping
    public FriendDto add(@RequestBody FriendDto dto) {
        return mapper.toDto(service.addFriend(mapper.toEntity(dto)));
    }

    @DeleteMapping
    public void delete(@RequestBody FriendDto dto) {
        service.removeFriend(new FriendId(dto.getUser1Id(), dto.getFriendId()));
    }
}
