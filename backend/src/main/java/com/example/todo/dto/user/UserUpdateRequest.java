package com.example.todo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateRequest {

    @JsonProperty("user_name")
    private String userName;

    private String email;

    public UserUpdateRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}