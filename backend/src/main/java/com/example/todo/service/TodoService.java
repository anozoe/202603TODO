package com.example.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> getTodosByUserId(Integer userId) {
        return todoRepository.findByUserId(userId);
    }

    public Todo getTodoById(Integer id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Integer id, Todo updatedTodo) {
        Todo todo = todoRepository.findById(id).orElse(null);

        if (todo == null) {
            return null;
        }

        todo.setUserId(updatedTodo.getUserId());
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setImage(updatedTodo.getImage());
        todo.setUpdatedBy(updatedTodo.getUpdatedBy());

        return todoRepository.save(todo);
    }

    public boolean deleteTodo(Integer id) {
        Todo todo = todoRepository.findById(id).orElse(null);

        if (todo == null) {
            return false;
        }

        todoRepository.delete(todo);
        return true;
    }
}