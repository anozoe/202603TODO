package com.example.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.dto.user.UserRegisterRequest;
import com.example.todo.dto.user.UserRegisterResponse;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.dto.user.UserUpdateRequest;
import com.example.todo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    /*ユーザー取得GET /api/users/{id}*/
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {

        UserResponse response = userService.getUserById(id);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(response);
    }

    /*ユーザー登録POST /api/users/register*/
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(
            @RequestBody UserRegisterRequest request) {

        UserRegisterResponse response = userService.register(request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*ユーザー更新PUT /api/users/{id}*/
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserUpdateRequest request) {

        UserResponse response = userService.updateUser(id, request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }
}