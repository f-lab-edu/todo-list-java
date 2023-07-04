package com.example.todolistjava.controller;

import com.example.todolistjava.domain.Todo;
import com.example.todolistjava.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class TodoController {

    private final TodoService todoService = new TodoService();

    @GetMapping("/todos")
    @ResponseBody
    public List<Todo> readAll() {
        List<Todo> list = todoService.findTodos();
        return list;
    }

    @PostMapping("/todos")
    @ResponseBody
    public List<Todo> createOne(Todo todo) {
        todoService.addTodo(todo);
        List<Todo> list = todoService.findTodos();
        return list;
    }
}
