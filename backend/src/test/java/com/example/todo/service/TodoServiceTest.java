package com.example.todo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Todo createTodo(Integer id, Integer userId, String title, String description) {
        Todo todo = new Todo();
        todo.setId(id);
        todo.setUserId(userId);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setCreatedBy("system");
        todo.setUpdatedAt(LocalDateTime.now());
        todo.setUpdatedBy("system");
        return todo;
    }

    @Test
    @DisplayName("TODO一覧取得")
    void testGetAllTodos() {
        List<Todo> todos = Arrays.asList(
                createTodo(1, 1, "aaa", "bbb"),
                createTodo(2, 2, "ccc", "ddd")
        );

        when(todoRepository.findAll()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos();

        assertEquals(2, result.size());
        assertEquals("aaa", result.get(0).getTitle());
    }

    @Test
    @DisplayName("特定ユーザTODO一覧取得")
    void testGetTodosByUserId() {
        List<Todo> todos = Arrays.asList(
                createTodo(1, 1, "aaa", "bbb"),
                createTodo(2, 1, "ccc", "ddd")
        );

        when(todoRepository.findByUserId(1)).thenReturn(todos);

        List<Todo> result = todoService.getTodosByUserId(1);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(1, result.get(1).getUserId());
    }

    @Test
    @DisplayName("TODO登録")
    void testCreateTodo() {
        Todo request = createTodo(null, 1, "登録テスト", "内容");
        Todo saved = createTodo(1, 1, "登録テスト", "内容");

        when(todoRepository.save(any(Todo.class))).thenReturn(saved);

        Todo result = todoService.createTodo(request);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("登録テスト", result.getTitle());
    }

    @Test
    @DisplayName("TODO更新")
    void testUpdateTodo() {
        Todo existing = createTodo(1, 1, "旧タイトル", "旧説明");
        Todo request = createTodo(null, 1, "新タイトル", "新説明");
        Todo updated = createTodo(1, 1, "新タイトル", "新説明");

        when(todoRepository.findById(1)).thenReturn(Optional.of(existing));
        when(todoRepository.save(any(Todo.class))).thenReturn(updated);

        Todo result = todoService.updateTodo(1, request);

        assertNotNull(result);
        assertEquals("新タイトル", result.getTitle());
        assertEquals("新説明", result.getDescription());
    }

    @Test
    @DisplayName("存在しないTODO更新はnull")
    void testUpdateTodoNotFound() {
        Todo request = createTodo(null, 1, "新タイトル", "新説明");

        when(todoRepository.findById(999)).thenReturn(Optional.empty());

        Todo result = todoService.updateTodo(999, request);

        assertNull(result);
    }

    @Test
    @DisplayName("TODO削除")
    void testDeleteTodo() {
        Todo existing = createTodo(1, 1, "削除対象", "内容");

        when(todoRepository.findById(1)).thenReturn(Optional.of(existing));

        boolean result = todoService.deleteTodo(1);

        assertTrue(result);
    }

    @Test
    @DisplayName("存在しないTODO削除はfalse")
    void testDeleteTodoNotFound() {
        when(todoRepository.findById(999)).thenReturn(Optional.empty());

        boolean result = todoService.deleteTodo(999);

        assertFalse(result);
    }
}