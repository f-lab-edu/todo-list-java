package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.example.todolistjava.connection.ConnectionConst.*;

public class TodoRepositoryTest {

    TodoRepository repository;

    @BeforeEach
    void beforeEach() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new TodoRepository(dataSource);
    }

    @Test
    void save() throws SQLException {

        Todo todo = new Todo("1", "a", false, false);
        repository.save(todo);
    }
}
