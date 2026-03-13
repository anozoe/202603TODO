package com.example.todo.service;

import org.springframework.stereotype.Service;

import com.example.todo.dto.auth.LoginRequest;
import com.example.todo.dto.auth.LoginResponse;

@Service
public class AuthService {

    public LoginResponse login(LoginRequest request) {

        if (request == null
                || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return null;
        }

        // 仮ログイン用
        if ("test@example.com".equals(request.getEmail())
                && "password123".equals(request.getPassword())) {
            return new LoginResponse(1, "テストユーザー", "test@example.com");
        }

        return null;
    }
}