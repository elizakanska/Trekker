package com.trek.ker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trek.ker.entity.User;
import com.trek.ker.entity.dto.UserDto;
import com.trek.ker.mapper.UserMapper;
import com.trek.ker.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.List.of;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserService service;

    @MockitoBean
    private UserMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/users — returns list of UserDto")
    void getAll_returnsList() throws Exception {
        User u1 = new User();
        User u2 = new User();
        UserDto dto1 = new UserDto(
                1L,              // id
                "Alice",         // username
                null,            // password (not relevant here)
                "alice@example.com", // email
                null             // role
        );
        UserDto dto2 = new UserDto(
                2L,
                "Bob",
                null,
                "bob@example.com",
                null
        );

        when(service.getAllUsers()).thenReturn(of(u1, u2));
        when(mapper.toDto(u1)).thenReturn(dto1);
        when(mapper.toDto(u2)).thenReturn(dto2);

        mvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("alice@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Bob"));

        verify(service).getAllUsers();
        verify(mapper).toDto(u1);
        verify(mapper).toDto(u2);
    }

    @Test
    @DisplayName("GET /api/users/{id} — returns single UserDto")
    void getById_returnsDto() throws Exception {
        long id = 7L;
        User user = new User();
        UserDto dto  = new UserDto(
                id,
                "Charlie",
                null,
                "charlie@example.com",
                null
        );

        when(service.getUserById(id)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(dto);

        mvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.email").value("charlie@example.com"))
                .andExpect(jsonPath("$.name").value("Charlie"));

        verify(service).getUserById(id);
        verify(mapper).toDto(user);
    }

    @Test
    @DisplayName("POST /api/users — creates and returns UserDto")
    void create_returnsCreatedDto() throws Exception {
        UserDto inDto  = new UserDto(
                null,            // new user has no id yet
                "Dave",
                null,
                "dave@example.com",
                null
        );
        User entity = new User();
        UserDto outDto = new UserDto(
                9L,
                "Dave",
                null,
                "dave@example.com",
                null
        );

        when(mapper.toEntity(inDto)).thenReturn(entity);
        when(service.saveUser(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(outDto);

        mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(9))
                .andExpect(jsonPath("$.email").value("dave@example.com"))
                .andExpect(jsonPath("$.name").value("Dave"));

        verify(mapper).toEntity(inDto);
        verify(service).saveUser(entity);
        verify(mapper).toDto(entity);
    }

    @Test
    @DisplayName("DELETE /api/users/{id} — deletes and returns 200")
    void delete_invokesService() throws Exception {
        long id = 11L;

        mvc.perform(delete("/api/users/{id}", id))
                .andExpect(status().isOk());

        verify(service).deleteUser(id);
    }
}
