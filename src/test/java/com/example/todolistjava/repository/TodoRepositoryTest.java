package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static com.example.todolistjava.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
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
    void crud() throws SQLException {

        //save
        Todo todo = new Todo("1", "a", false, false);
        repository.save(todo);

        //findById
        Todo findTodo = repository.findById(todo.getId());
        assertThat(findTodo).isNotNull();

        //update: text: "a" -> "b"
        repository.update(todo.getId(), "b");
        Todo updatedTodo = repository.findById(todo.getId());
        assertThat(updatedTodo.getText()).isEqualTo("b");

        //delete
        repository.delete(todo.getId());
        assertThatThrownBy(() -> repository.findById(todo.getId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}
