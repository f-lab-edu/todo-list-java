package com.example.todolistjava.service;

import com.example.todolistjava.domain.Todo;
import com.example.todolistjava.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * 추가
     */
    public Todo addTodo(Todo todo) throws SQLException {
        return todoRepository.save(todo);
    }

    public Todo findTodo(String id) throws SQLException {
        return todoRepository.findById(id);
    }

    public void editTodo(String id, String text) throws SQLException {
        todoRepository.update(id, text);
    }

    public void deleteTodo(String id) throws SQLException {
        todoRepository.delete(id);
    }

}
