package com.example.todolistjava.controller;

import com.example.todolistjava.domain.Todo;
import com.example.todolistjava.service.TodoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Todo createOne(
            @RequestParam("id") String id,
            @RequestParam("text") String text,
            @RequestParam("isCompleted") boolean isCompleted,
            @RequestParam("isEdit") boolean isEdit) throws SQLException {

        Todo todo = new Todo(id, text, isCompleted, isEdit);
        return todoService.addTodo(todo);
    }

    @PatchMapping("/todos")
    public void updateOne(
            @RequestParam String id,
            @RequestParam String text) throws SQLException {
        todoService.editTodo(id, text);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteOne(@PathVariable String id) throws SQLException {
        todoService.deleteTodo(id);
    }
}
