package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodoRepositoryTest {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/todo_data?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    TodoRepository repository;
    Todo todo = new Todo("1", "a", false, false);

    @BeforeEach
    void beforeEach() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new TodoRepository(dataSource);
    }

    @Test
    @Order(1)
    void save() throws SQLException {
        repository.save(todo);
        Todo findTodo = repository.findById(todo.getId());
        assertThat(findTodo).isNotNull();
    }

    @Test
    @Order(2)
    void update() throws SQLException {
        repository.update(todo.getId(), "b");
        Todo updatedTodo = repository.findById(todo.getId());
        assertThat(updatedTodo.getText()).isEqualTo("b");
    }

    @Test
    @Order(3)
    void delete() throws SQLException {
        repository.delete(todo.getId());
        assertThatThrownBy(() -> repository.findById(todo.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}
