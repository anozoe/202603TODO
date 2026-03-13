package com.example.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.dto.user.UserRegisterRequest;
import com.example.todo.dto.user.UserRegisterResponse;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.dto.user.UserUpdateRequest;
import com.example.todo.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        UserResponse response = userService.getUserById(id);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ユーザーが見つかりません。");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = userService.register(request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("入力内容が不正です。");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Integer id,
            @RequestBody UserUpdateRequest request) {

        UserResponse response = userService.updateUser(id, request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ユーザー更新に失敗しました。");
        }

        return ResponseEntity.ok(response);
    }
}