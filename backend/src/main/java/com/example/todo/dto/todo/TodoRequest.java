package com.example.todo.dto.todo;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoRequest {
    @NotNull
    private Integer userId;
    @NotNull
    @Size(max = 30, message = "30文字以下で入力してください")
    private String title;
    @NotNull
    @Size(max = 150, message = "150文字以下で入力してください")
    private String description;
    private byte[] image;
}