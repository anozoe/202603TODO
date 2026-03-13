package com.example.todo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

    private Integer id;

    @JsonProperty("user_name")
    private String userName;

    private String email;

    public UserResponse() {
    }

    public UserResponse(Integer id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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