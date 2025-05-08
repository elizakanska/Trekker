package com.trek.ker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trek.ker.entity.Favorite;
import com.trek.ker.entity.dto.FavoriteDto;
import com.trek.ker.entity.id.FavoriteId;
import com.trek.ker.mapper.FavoriteMapper;
import com.trek.ker.service.FavoriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.List.of;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FavoriteController.class)
class FavoriteControllerTest {

    private static final Long TEST_USER_ID   = 3L;
    private static final Long TEST_TRAIL_ID1 = 9L;

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private FavoriteService service;

    @MockitoBean
    private FavoriteMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/favorites — success returns 200 and body")
    void addFavorite_success() throws Exception {
        FavoriteDto dto = new FavoriteDto();
        dto.setUserId(TEST_USER_ID);
        dto.setTrailId(TEST_TRAIL_ID1);

        Favorite entity = new Favorite();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(service.addFavorite(entity)).thenReturn(true);
        when(mapper.toDto(entity)).thenReturn(dto);

        mvc.perform(post("/api/favorites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));

        verify(mapper).toEntity(dto);
        verify(service).addFavorite(entity);
        verify(mapper).toDto(entity);
    }

    @Test
    @DisplayName("POST /api/favorites — failure returns 400")
    void addFavorite_failure() throws Exception {
        FavoriteDto dto = new FavoriteDto();
        dto.setUserId(TEST_USER_ID);
        dto.setTrailId(TEST_TRAIL_ID1);

        when(mapper.toEntity(dto)).thenReturn(new Favorite());
        when(service.addFavorite(any())).thenReturn(false);

        mvc.perform(post("/api/favorites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(mapper).toEntity(dto);
        verify(service).addFavorite(any());
        verify(mapper, never()).toDto(any());
    }

    @Test
    @DisplayName("GET /api/favorites/user/{userId} — returns list of FavoriteDto")
    void getByUser_returnsDtos() throws Exception {
        Favorite fav1 = new Favorite();
        Favorite fav2 = new Favorite();

        FavoriteDto dto1 = new FavoriteDto();
        dto1.setUserId(TEST_USER_ID);
        dto1.setTrailId(TEST_TRAIL_ID1);

        FavoriteDto dto2 = new FavoriteDto();
        dto2.setUserId(TEST_USER_ID);
        dto2.setTrailId(TEST_TRAIL_ID1 + 1);

        when(service.getFavoritesByUser(TEST_USER_ID)).thenReturn(of(fav1, fav2));
        when(mapper.toDto(fav1)).thenReturn(dto1);
        when(mapper.toDto(fav2)).thenReturn(dto2);

        mvc.perform(get("/api/favorites/user/{userId}", TEST_USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId").value(TEST_USER_ID))
                .andExpect(jsonPath("$[0].trailId").value(TEST_TRAIL_ID1))
                .andExpect(jsonPath("$[1].trailId").value(TEST_TRAIL_ID1 + 1));

        verify(service).getFavoritesByUser(TEST_USER_ID);
        verify(mapper).toDto(fav1);
        verify(mapper).toDto(fav2);
    }

    @Test
    @DisplayName("DELETE /api/favorites — calls service.removeFavorite()")
    void deleteFavorite_invokesService() throws Exception {
        FavoriteDto dto = new FavoriteDto();
        dto.setUserId(TEST_USER_ID);
        dto.setTrailId(TEST_TRAIL_ID1);

        mvc.perform(delete("/api/favorites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        ArgumentCaptor<FavoriteId> captor = ArgumentCaptor.forClass(FavoriteId.class);
        verify(service).removeFavorite(captor.capture());
        FavoriteId passed = captor.getValue();

        Long actualUserId  = (Long) ReflectionTestUtils.getField(passed, "userId");
        Long actualTrailId = (Long) ReflectionTestUtils.getField(passed, "trailId");

        assertEquals(TEST_USER_ID,   actualUserId);
        assertEquals(TEST_TRAIL_ID1, actualTrailId);
    }
}
