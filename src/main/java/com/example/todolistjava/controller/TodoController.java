package com.example.todolistjava.controller;

import com.example.todolistjava.domain.Todo;
import com.example.todolistjava.service.TodoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos/{id}")
    public Todo readOne(@PathVariable String id) throws SQLException {
        return todoService.findTodo(id);
    }

    @PostMapping("/todos")
    public Todo createOne(@RequestBody Todo todo) throws SQLException {
        return todoService.addTodo(todo);
    }

    @PatchMapping("/todos")
    public void updateOne(@RequestBody Todo todo) throws SQLException {
        todoService.editTodo(todo.getId(), todo.getText());
    }

    @DeleteMapping("/todos/{id}")
    public void deleteOne(@PathVariable String id) throws SQLException {
        todoService.deleteTodo(id);
    }
}
