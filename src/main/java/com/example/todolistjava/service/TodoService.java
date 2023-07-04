package com.example.todolistjava.service;

import com.example.todolistjava.domain.Todo;
import com.example.todolistjava.repository.MemoryTodoRepository;
import com.example.todolistjava.repository.TodoRepository;

import java.util.List;

public class TodoService {

    private final TodoRepository todoRepository = new MemoryTodoRepository();

    /**
     * 추가
     */
    public List<Todo> addTodo(Todo todo) {
        todoRepository.save(todo);
        return todoRepository.findAll();
    }

    /**
     * 전체 회원 조회
     */

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }
}
