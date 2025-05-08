package com.trek.ker.service;

import com.trek.ker.entity.User;
import com.trek.ker.entity.enums.Role;
import com.trek.ker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private String adminUsername = "admin";
    private String adminPassword = "adminPass";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole(Role.USER);
    }

    @Test
    void initAdmin_UserDoesNotExist_AdminCreated() {
        when(userRepo.existsByUsername(adminUsername)).thenReturn(false);

        userService.initAdmin();

        verify(userRepo).existsByUsername(adminUsername);
        verify(userRepo).save(any(User.class));
    }

    @Test
    void initAdmin_UserExists_NoActionTaken() {
        when(userRepo.existsByUsername(adminUsername)).thenReturn(true);

        userService.initAdmin();

        verify(userRepo, times(1)).existsByUsername(adminUsername);
        verify(userRepo, never()).save(any(User.class));
    }

    @Test
    void getAllUsers_ReturnsUserList() {
        List<User> users = Arrays.asList(user, new User());

        when(userRepo.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepo).findAll();
    }

    @Test
    void getUserById_ExistingId_ReturnsUser() {
        Long id = 1L;

        when(userRepo.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getUserById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(userRepo).findById(id);
    }

    @Test
    void getUserById_NonExistingId_ReturnsNull() {
        Long id = 999L;

        when(userRepo.findById(id)).thenReturn(Optional.empty());

        User result = userService.getUserById(id);

        assertNull(result);
        verify(userRepo).findById(id);
    }

    @Test
    void saveUser_SavesUser() {
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        when(userRepo.save(user)).thenReturn(user);

        user.setPassword(rawPassword);
        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals(encodedPassword, result.getPassword());
        assertEquals(Role.USER, result.getRole());
        verify(passwordEncoder).encode(rawPassword);
        verify(userRepo).save(user);
    }

    @Test
    void deleteUser_DeletesById() {
        Long id = 1L;

        doNothing().when(userRepo).deleteById(id);

        userService.deleteUser(id);

        verify(userRepo).deleteById(id);
    }

    @Test
    void findByEmail_ReturnsUser() {
        String email = "test@example.com";

        when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepo).findByEmail(email);
    }
}
