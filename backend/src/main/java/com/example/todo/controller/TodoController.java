package com.example.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /*TODO一覧取得GET /api/todos*/
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    /*特定ユーザTODO一覧取得GET /api/todos/user/{userId}*/
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Todo>> getTodosByUserId(@PathVariable Integer userId) {
        List<Todo> todos = todoService.getTodosByUserId(userId);
        return ResponseEntity.ok(todos);
    }

    /*TODO登録POST /api/todos*/
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    /*TODO更新PUT /api/todos/{id}*/
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {

        Todo updatedTodo = todoService.updateTodo(id, todo);

        if (updatedTodo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTodo);
    }

    /*TODO削除DELETE /api/todos/{id}*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {

        boolean deleted = todoService.deleteTodo(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}