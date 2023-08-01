package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TodoRepositoryTest {
    TodoRepository repository = new TodoRepository();

    @Test
    void save() throws SQLException {

        Todo todo = new Todo("3", "b", false, true);
        repository.save(todo);
    }
}
