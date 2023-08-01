package com.example.todolistjava.service;

import com.example.todolistjava.domain.Todo;
import com.example.todolistjava.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * 추가
     */
    public List<Todo> addTodo(Todo todo) throws SQLException {
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
