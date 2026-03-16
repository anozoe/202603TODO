package com.example.todo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.todo.dto.user.UserRegisterRequest;
import com.example.todo.dto.user.UserRegisterResponse;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.dto.user.UserUpdateRequest;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private User createUser(Integer id, String userName, String email, String password) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("system");
        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy("system");
        return user;
    }

    @Test
    @DisplayName("ユーザー取得成功")
    void testGetUserById() {
        User user = createUser(1, "テストユーザー", "test@example.com", "password123");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserResponse result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("テストユーザー", result.getUserName());
    }

    @Test
    @DisplayName("ユーザー登録成功")
    void testRegister() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUserName("柚木");
        request.setEmail("yunoki@example.com");
        request.setPassword("123456");

        when(userRepository.findByEmail("yunoki@example.com")).thenReturn(Optional.empty());

        UserRegisterResponse result = userService.register(request);

        assertNotNull(result);
        assertEquals("ユーザー登録に成功しました。", result.getMessage());
    }

    @Test
    @DisplayName("重複メールは登録失敗")
    void testRegisterDuplicateEmail() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUserName("柚木");
        request.setEmail("yunoki@example.com");
        request.setPassword("123456");

        User existing = createUser(1, "既存", "yunoki@example.com", "pass");

        when(userRepository.findByEmail("yunoki@example.com")).thenReturn(Optional.of(existing));

        UserRegisterResponse result = userService.register(request);

        assertNull(result);
    }

    @Test
    @DisplayName("ユーザー更新成功")
    void testUpdateUser() {
        User existing = createUser(1, "旧ユーザー", "old@example.com", "pass");

        UserUpdateRequest request = new UserUpdateRequest();
        request.setUserName("新ユーザー");
        request.setEmail("new@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(existing));
        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(existing)).thenReturn(existing);

        UserResponse result = userService.updateUser(1, request);

        assertNotNull(result);
        assertEquals("新ユーザー", result.getUserName());
        assertEquals("new@example.com", result.getEmail());
    }
}