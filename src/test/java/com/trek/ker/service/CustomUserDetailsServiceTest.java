package com.trek.ker.service;

import com.trek.ker.entity.User;
import com.trek.ker.entity.enums.Role;
import com.trek.ker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    private UserRepository userRepo;
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userRepo = mock(UserRepository.class);
        userDetailsService = new CustomUserDetailsService(userRepo);
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password123");
        mockUser.setRole(Role.USER);

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("USER")));
    }

    @Test
    void loadUserByUsername_UserNotFound_ThrowsException() {
        when(userRepo.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
                userDetailsService.loadUserByUsername("missing@example.com"));
    }
}
