//package com.trek.ker.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.trek.ker.entity.Friend;
//import com.trek.ker.entity.dto.FriendDto;
//import com.trek.ker.entity.id.FriendId;
//import com.trek.ker.mapper.FriendMapper;
//import com.trek.ker.service.FriendService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static java.util.List.of;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(FriendController.class)
//class FriendControllerTest {
//
//    // shared test‐fixture IDs
//    private static final Long TEST_USER_ID      = 42L;
//    private static final Long TEST_FRIEND_ID_1  = 7L;
//    private static final Long TEST_FRIEND_ID_2  = 8L;
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockitoBean
//    private FriendService service;
//
//    @MockitoBean
//    private FriendMapper mapper;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    @DisplayName("GET /api/friends/user/{userId} — returns list of FriendDto")
//    void getAllByUser_returnsDtos() throws Exception {
//        Friend e1 = new Friend();
//        Friend e2 = new Friend();
//
//        FriendDto dto1 = new FriendDto();
//        dto1.setUser1Id(TEST_USER_ID);
//        dto1.setFriendId(TEST_FRIEND_ID_1);
//
//        FriendDto dto2 = new FriendDto();
//        dto2.setUser1Id(TEST_USER_ID);
//        dto2.setFriendId(TEST_FRIEND_ID_2);
//
//        when(service.getFriendshipsForUser(TEST_USER_ID)).thenReturn(of(e1, e2));
//        when(mapper.toDto(e1)).thenReturn(dto1);
//        when(mapper.toDto(e2)).thenReturn(dto2);
//
//        mvc.perform(get("/api/friends/user/{userId}", TEST_USER_ID))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].user1Id").value(TEST_USER_ID))
//                .andExpect(jsonPath("$[0].friendId").value(TEST_FRIEND_ID_1))
//                .andExpect(jsonPath("$[1].friendId").value(TEST_FRIEND_ID_2));
//
//        verify(service).getFriendshipsForUser(TEST_USER_ID);
//        verify(mapper).toDto(e1);
//        verify(mapper).toDto(e2);
//    }
//
//    @Test
//    @DisplayName("POST /api/friends — success returns 200 and body")
//    void addFriend_success() throws Exception {
//        FriendDto inDto = new FriendDto();
//        inDto.setUser1Id(TEST_USER_ID);
//        inDto.setFriendId(TEST_FRIEND_ID_1);
//
//        Friend entity = new Friend();
//
//        when(mapper.toEntity(inDto)).thenReturn(entity);
//        when(service.addFriendship(entity)).thenReturn(true);
//        when(mapper.toDto(entity)).thenReturn(inDto);
//
//        mvc.perform(post("/api/friends")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(inDto)));
//
//        verify(mapper).toEntity(inDto);
//        verify(service).addFriendship(entity);
//        verify(mapper).toDto(entity);
//    }
//
//    @Test
//    @DisplayName("POST /api/friends — failure returns 400")
//    void addFriend_failure() throws Exception {
//        FriendDto inDto = new FriendDto();
//        inDto.setUser1Id(TEST_USER_ID);
//        inDto.setFriendId(TEST_FRIEND_ID_2);
//
//        when(mapper.toEntity(inDto)).thenReturn(new Friend());
//        when(service.addFriendship(any())).thenReturn(false);
//
//        mvc.perform(post("/api/friends")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inDto)))
//                .andExpect(status().isBadRequest());
//
//        verify(mapper).toEntity(inDto);
//        verify(service).addFriendship(any());
//        verify(mapper, never()).toDto(any());
//    }
//
//    @Test
//    @DisplayName("DELETE /api/friends — invokes removeFriend with correct FriendId")
//    void deleteFriend_invokesService() throws Exception {
//        FriendDto inDto = new FriendDto();
//        inDto.setUser1Id(TEST_USER_ID);
//        inDto.setFriendId(TEST_FRIEND_ID_1);
//
//        mvc.perform(delete("/api/friends")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inDto)))
//                .andExpect(status().isOk());
//
//        ArgumentCaptor<FriendId> captor = ArgumentCaptor.forClass(FriendId.class);
//        verify(service).removeFriend(captor.capture());
//
//        // assert via equals() — no need for getters
//        FriendId expected = new FriendId(TEST_USER_ID, TEST_FRIEND_ID_1);
//        assertEquals(expected, captor.getValue());
//    }
//}
