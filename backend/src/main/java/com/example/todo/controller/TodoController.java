package com.example.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // TODO一覧取得
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    // 特定ユーザTODO一覧取得
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Todo>> getTodosByUserId(@PathVariable Integer userId) {
        List<Todo> todos = todoService.getTodosByUserId(userId);
        return ResponseEntity.ok(todos);
    }

    // TODO詳細取得
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);

        if (todo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(todo);
    }

    // TODO登録
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    // TODO更新
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(id, todo);

        if (updatedTodo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTodo);
    }

    // TODO削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        boolean deleted = todoService.deleteTodo(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}