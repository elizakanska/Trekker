//package com.trek.ker.service;
//
//import com.trek.ker.entity.Session;
//import com.trek.ker.entity.User;
//import com.trek.ker.entity.id.SessionId;
//import com.trek.ker.repository.SessionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class SessionServiceTest {
//
//    private SessionRepository sessionRepository;
//    private SessionService sessionService;
//
//    @BeforeEach
//    void setUp() {
//        sessionRepository = mock(SessionRepository.class);
//        sessionService = new SessionService(sessionRepository);
//    }
//
//    @Test
//    void getActiveSessions_ReturnsCombinedSessions() {
//        User user = new User();
//        List<Session> sessionsAsUser1 = Arrays.asList(new Session(), new Session());
//        List<Session> sessionsAsUser2 = List.of(new Session());
//
//        when(sessionRepository.findByUser1(user)).thenReturn(sessionsAsUser1);
//        when(sessionRepository.findByUser2(user)).thenReturn(sessionsAsUser2);
//
//        List<Session> result = sessionService.getActiveSessions(user);
//
//        assertEquals(3, result.size());
//        verify(sessionRepository).findByUser1(user);
//        verify(sessionRepository).findByUser2(user);
//    }
//
//    @Test
//    void getSessionByInviteCode_Found_ReturnsSessionOptional() {
//        Session session = new Session();
//        Long inviteCode = 123456L;
//
//        when(sessionRepository.findByInviteCode(inviteCode)).thenReturn(session);
//
//        Optional<Session> result = sessionService.getSessionByInviteCode(inviteCode);
//
//        assertTrue(result.isPresent());
//        assertEquals(session, result.get());
//    }
//
//    @Test
//    void getSessionByInviteCode_NotFound_ReturnsEmptyOptional() {
//        Long inviteCode = 999L;
//
//        when(sessionRepository.findByInviteCode(inviteCode)).thenReturn(null);
//
//        Optional<Session> result = sessionService.getSessionByInviteCode(inviteCode);
//
//        assertFalse(result.isPresent());
//    }
//
//    @Test
//    void createSession_Success_ReturnsTrue() {
//        Session session = new Session();
//
//        doNothing().when(sessionRepository).save(session);
//
//        boolean result = sessionService.createSession(session);
//
//        assertTrue(result);
//        verify(sessionRepository).save(session);
//    }
//
//    @Test
//    void createSession_ExceptionThrown_ReturnsFalse() {
//        Session session = new Session();
//
//        doThrow(new RuntimeException("DB error")).when(sessionRepository).save(session);
//
//        boolean result = sessionService.createSession(session);
//
//        assertFalse(result);
//        verify(sessionRepository).save(session);
//    }
//
//    @Test
//    void getSessionById_ReturnsSessionOptional() {
//        SessionId id = new SessionId(1L, 2L);
//        Session session = new Session();
//
//        when(sessionRepository.findById(id)).thenReturn(Optional.of(session));
//
//        Optional<Session> result = sessionService.getSessionById(id);
//
//        assertTrue(result.isPresent());
//        assertEquals(session, result.get());
//    }
//
//    @Test
//    void deleteSession_Success_ReturnsTrue() {
//        SessionId id = new SessionId(1L, 2L);
//
//        doNothing().when(sessionRepository).deleteById(id);
//
//        boolean result = sessionService.deleteSession(id);
//
//        assertTrue(result);
//        verify(sessionRepository).deleteById(id);
//    }
//
//    @Test
//    void deleteSession_ExceptionThrown_ReturnsFalse() {
//        SessionId id = new SessionId(1L, 2L);
//
//        doThrow(new RuntimeException("DB error")).when(sessionRepository).deleteById(id);
//
//        boolean result = sessionService.deleteSession(id);
//
//        assertFalse(result);
//        verify(sessionRepository).deleteById(id);
//    }
//}
