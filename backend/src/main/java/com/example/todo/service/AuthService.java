package com.example.todo.service;

import org.springframework.stereotype.Service;

import com.example.todo.dto.auth.LoginRequest;
import com.example.todo.dto.auth.LoginResponse;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        if (request == null
                || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return null;
        }

        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return null;
        }

        return new LoginResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }
}