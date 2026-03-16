package com.example.todo.service;

import org.springframework.stereotype.Service;

import com.example.todo.dto.user.UserRegisterRequest;
import com.example.todo.dto.user.UserRegisterResponse;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.dto.user.UserUpdateRequest;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }

    public UserRegisterResponse register(UserRegisterRequest request) {

        if (request == null
                || request.getUserName() == null || request.getUserName().isBlank()
                || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return null;
        }

        // 既に同じメールアドレスが存在する場合
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return null;
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCreatedBy("system");
        user.setUpdatedBy("system");

        userRepository.save(user);

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

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        // 他ユーザーのメールアドレスと重複していないか確認
        User existingUser = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (existingUser != null && !existingUser.getId().equals(id)) {
            return null;
        }

        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setUpdatedBy("system");

        User updatedUser = userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getUserName(),
                updatedUser.getEmail()
        );
    }
}