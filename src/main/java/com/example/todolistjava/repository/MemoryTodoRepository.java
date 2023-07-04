package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;

import java.util.*;


public class MemoryTodoRepository implements TodoRepository{

    List<Todo> store = new ArrayList<>(){{
        add(new Todo("1", "a", false, false));
        add(new Todo("2", "b", false, false));
        add(new Todo("3", "c", false, false));
    }};

    @Override
    public void save(Todo todo) {
        store.add(todo);
    }

    @Override
    public List<Todo> findAll() {
        return store;
    }
}
