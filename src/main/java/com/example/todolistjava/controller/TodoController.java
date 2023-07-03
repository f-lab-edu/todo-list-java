package com.example.todolistjava.controller;

import com.example.todolistjava.domain.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class TodoController {
    @GetMapping("/api")
    @ResponseBody
    public List<Todo> getTodo() {
        List<Todo> list = new ArrayList<>();
        Todo todo1 = new Todo("1", "a", false, false);
        Todo todo2 = new Todo("2", "b", false, false);
        Todo todo3 = new Todo("3", "c", false, false);

        list.add(todo1);
        list.add(todo2);
        list.add(todo3);

        return list;
    }

}
