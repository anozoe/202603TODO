package com.example.todo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterRequest {

    @JsonProperty("user_name")
    private String userName;

    private String email;
    private String password;

    public UserRegisterRequest() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}