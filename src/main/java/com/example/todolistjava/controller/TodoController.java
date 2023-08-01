package com.example.todolistjava.controller;

import com.example.todolistjava.domain.Todo;
import com.example.todolistjava.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<Todo> readAll() {
        List<Todo> list = todoService.findTodos();
        return list;
    }

    @PostMapping("/todos")
    public List<Todo> createOne(Todo todo) throws SQLException {
        List<Todo> list = todoService.addTodo(todo);
        return list;
    }
}
