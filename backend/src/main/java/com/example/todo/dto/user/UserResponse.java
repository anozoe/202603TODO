package com.example.todo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String userName;
    private String email;

}