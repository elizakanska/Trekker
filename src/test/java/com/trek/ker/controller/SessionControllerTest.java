//package com.trek.ker.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.trek.ker.entity.Session;
//import com.trek.ker.entity.User;
//import com.trek.ker.entity.dto.SessionDto;
//import com.trek.ker.entity.id.SessionId;
//import com.trek.ker.mapper.SessionMapper;
//import com.trek.ker.service.SessionService;
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
//import java.util.Optional;
//
//import static java.util.List.of;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(SessionController.class)
//class SessionControllerTest {
//
//    // shared test fixtures
//    private static final Long TEST_USER_ID        = 99L;
//    private static final Long TEST_INVITE_CODE    = 123L;
//    private static final Long TEST_INVITE_CODE_2  = 456L;
//    private static final Long TEST_SESSION_ID_1   = 1L;
//    private static final Long TEST_SESSION_ID_2   = 2L;
//    private static final Long TEST_USER1          = 9L;
//    private static final Long TEST_USER2          = 10L;
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockitoBean
//    private SessionService sessionService;
//
//    @MockitoBean
//    private SessionMapper sessionMapper;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("GET /api/sessions/user/{userId} → list of SessionDto")
//    void getActiveSessions_returnsList() throws Exception {
//        Session s1 = new Session();
//        Session s2 = new Session();
//
//        SessionDto dto1 = new SessionDto(
//                TEST_SESSION_ID_1,
//                TEST_INVITE_CODE,
//                TEST_USER_ID,
//                TEST_USER1
//        );
//        SessionDto dto2 = new SessionDto(
//                TEST_SESSION_ID_2,
//                TEST_INVITE_CODE_2,
//                TEST_USER_ID,
//                TEST_USER2
//        );
//
//        when(sessionService.getActiveSessions(any(User.class)))
//                .thenReturn(of(s1, s2));
//        when(sessionMapper.toDto(s1)).thenReturn(dto1);
//        when(sessionMapper.toDto(s2)).thenReturn(dto2);
//
//        mvc.perform(get("/api/sessions/user/{userId}", TEST_USER_ID))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].inviteCode").value(TEST_INVITE_CODE))
//                .andExpect(jsonPath("$[1].inviteCode").value(TEST_INVITE_CODE_2));
//
//        verify(sessionService).getActiveSessions(any(User.class));
//        verify(sessionMapper, times(2)).toDto(any(Session.class));
//    }
//
//    @Test
//    @DisplayName("GET /api/sessions/invite/{code} → found")
//    void getByInviteCode_found() throws Exception {
//        Session session = new Session();
//        SessionDto dto = new SessionDto(
//                TEST_SESSION_ID_1,
//                TEST_INVITE_CODE,
//                TEST_USER1,
//                TEST_USER2
//        );
//
//        when(sessionService.getSessionByInviteCode(TEST_INVITE_CODE))
//                .thenReturn(Optional.of(session));
//        when(sessionMapper.toDto(session)).thenReturn(dto);
//
//        mvc.perform(get("/api/sessions/invite/{inviteCode}", TEST_INVITE_CODE))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
//
//        verify(sessionService).getSessionByInviteCode(TEST_INVITE_CODE);
//        verify(sessionMapper).toDto(session);
//    }
//
//    @Test
//    @DisplayName("GET /api/sessions/invite/{code} → not found")
//    void getByInviteCode_notFound() throws Exception {
//        when(sessionService.getSessionByInviteCode(TEST_INVITE_CODE))
//                .thenReturn(Optional.empty());
//
//        mvc.perform(get("/api/sessions/invite/{inviteCode}", TEST_INVITE_CODE))
//                .andExpect(status().isNotFound());
//
//        verify(sessionService).getSessionByInviteCode(TEST_INVITE_CODE);
//        verifyNoInteractions(sessionMapper);
//    }
//
//    @Test
//    @DisplayName("POST /api/sessions → created")
//    void createSession_success() throws Exception {
//        SessionDto inDto = new SessionDto(
//                null,
//                TEST_INVITE_CODE,
//                TEST_USER1,
//                TEST_USER2
//        );
//        Session entity = new Session();
//
//        when(sessionMapper.toEntity(inDto)).thenReturn(entity);
//        when(sessionService.createSession(entity)).thenReturn(true);
//        when(sessionMapper.toDto(entity)).thenReturn(inDto);
//
//        mvc.perform(post("/api/sessions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(inDto)));
//
//        verify(sessionMapper).toEntity(inDto);
//        verify(sessionService).createSession(entity);
//        verify(sessionMapper).toDto(entity);
//    }
//
//    @Test
//    @DisplayName("POST /api/sessions → bad request")
//    void createSession_failure() throws Exception {
//        SessionDto inDto = new SessionDto(
//                TEST_SESSION_ID_1,
//                TEST_INVITE_CODE,
//                TEST_USER1,
//                TEST_USER2
//        );
//
//        when(sessionMapper.toEntity(inDto)).thenReturn(new Session());
//        when(sessionService.createSession(any())).thenReturn(false);
//
//        mvc.perform(post("/api/sessions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inDto)))
//                .andExpect(status().isBadRequest());
//
//        verify(sessionMapper).toEntity(inDto);
//        verify(sessionService).createSession(any());
//        verifyNoMoreInteractions(sessionMapper);
//    }
//
//    @Test
//    @DisplayName("GET /api/sessions/{u1}/{u2} → found")
//    void getById_found() throws Exception {
//        SessionId sid = new SessionId(TEST_USER1, TEST_USER2);
//        Session session = new Session();
//        SessionDto dto = new SessionDto(
//                TEST_SESSION_ID_1,
//                TEST_INVITE_CODE,
//                TEST_USER1,
//                TEST_USER2
//        );
//
//        when(sessionService.getSessionById(sid)).thenReturn(Optional.of(session));
//        when(sessionMapper.toDto(session)).thenReturn(dto);
//
//        mvc.perform(get("/api/sessions/{u1}/{u2}", TEST_USER1, TEST_USER2))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
//
//        verify(sessionService).getSessionById(sid);
//        verify(sessionMapper).toDto(session);
//    }
//
//    @Test
//    @DisplayName("GET /api/sessions/{u1}/{u2} → not found")
//    void getById_notFound() throws Exception {
//        SessionId sid = new SessionId(TEST_USER1, TEST_USER2);
//        when(sessionService.getSessionById(sid)).thenReturn(Optional.empty());
//
//        mvc.perform(get("/api/sessions/{u1}/{u2}", TEST_USER1, TEST_USER2))
//                .andExpect(status().isNotFound());
//
//        verify(sessionService).getSessionById(sid);
//        verifyNoInteractions(sessionMapper);
//    }
//
//    @Test
//    @DisplayName("DELETE /api/sessions → ok when deleted")
//    void deleteSession_ok() throws Exception {
//        SessionDto dto = new SessionDto(
//                null,
//                TEST_INVITE_CODE,
//                TEST_USER1,
//                TEST_USER2
//        );
//
//        when(sessionService.deleteSession(new SessionId(TEST_USER1, TEST_USER2)))
//                .thenReturn(true);
//
//        mvc.perform(delete("/api/sessions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk());
//
//// capture the SessionId passed to the service
//        ArgumentCaptor<SessionId> cap = ArgumentCaptor.forClass(SessionId.class);
//        verify(sessionService).deleteSession(cap.capture());
//        SessionId passed = cap.getValue();
//
//// instead of calling getters (which don’t exist), just compare via equals():
//        assertThat(passed)
//                .isEqualTo(new SessionId(TEST_USER1, TEST_USER2));
//
//    }
//
//    @Test
//    @DisplayName("DELETE /api/sessions → not found when absent")
//    void deleteSession_notFound() throws Exception {
//        SessionDto dto = new SessionDto(
//                null,
//                TEST_INVITE_CODE,
//                TEST_USER1,
//                TEST_USER2
//        );
//
//        when(sessionService.deleteSession(new SessionId(TEST_USER1, TEST_USER2)))
//                .thenReturn(false);
//
//        mvc.perform(delete("/api/sessions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isNotFound());
//    }
//}
