package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;

import java.util.List;

public interface TodoRepository {
    void save(Todo todo);
    List<Todo> findAll();
}
