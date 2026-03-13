package com.example.todo.service;

import org.springframework.stereotype.Service;

import com.example.todo.dto.user.UserRegisterRequest;
import com.example.todo.dto.user.UserRegisterResponse;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.dto.user.UserUpdateRequest;

@Service
public class UserService {

    public UserResponse getUserById(Integer id) {
        if (id != null && id == 1) {
            return new UserResponse(1, "テストユーザー", "test@example.com");
        }
        return null;
    }

    public UserRegisterResponse register(UserRegisterRequest request) {

        if (request == null
                || request.getUserName() == null || request.getUserName().isBlank()
                || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return null;
        }

        return new UserRegisterResponse("ユーザー登録に成功しました。");
    }

    public UserResponse updateUser(Integer id, UserUpdateRequest request) {

        if (id == null || request == null) {
            return null;
        }

        if (request.getUserName() == null || request.getUserName().isBlank()
                || request.getEmail() == null || request.getEmail().isBlank()) {
            return null;
        }

        if (id == 1) {
            return new UserResponse(id, request.getUserName(), request.getEmail());
        }

        return null;
    }
}