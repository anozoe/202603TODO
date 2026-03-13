package com.example.todo.dto.user;

public class UserRegisterResponse {

    private String message;

    public UserRegisterResponse() {
    }

    public UserRegisterResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}