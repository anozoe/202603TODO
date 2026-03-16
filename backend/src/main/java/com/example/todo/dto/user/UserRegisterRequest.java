package com.example.todo.dto.user;

import lombok.Data;

@Data
public class UserRegisterRequest {

    private String userName;
    private String email;
    private String password;

}