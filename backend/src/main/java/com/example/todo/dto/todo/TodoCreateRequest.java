package com.example.todo.dto.todo;

import lombok.Data;

@Data
public class TodoCreateRequest {

    private Integer userId;
    private String title;
    private String description;
    private String createdBy;
    private String updatedBy;

}