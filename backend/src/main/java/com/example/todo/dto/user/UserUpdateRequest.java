package com.example.todo.dto.user;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String userName;
    private String email;

}