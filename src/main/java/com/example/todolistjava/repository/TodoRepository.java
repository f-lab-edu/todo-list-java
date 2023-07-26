package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepository {

    List<Todo> store = new ArrayList<>(){{
        add(new Todo("1", "a", false, false));
        add(new Todo("2", "b", false, false));
        add(new Todo("3", "c", false, false));
    }};

    public void save(Todo todo) {
        store.add(todo);
    }

    public List<Todo> findAll() {
        return store;
    }
}
