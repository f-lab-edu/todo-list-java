package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
@Slf4j
public class TodoRepository {

    private final JdbcTemplate template;

    public TodoRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public Todo save(Todo todo) throws SQLException {
        String sql = "insert into todo_table(id, text, isCompleted, isEdit) values (?, ?, ?, ?)";
        template.update(sql, todo.getId(), todo.getText(), todo.isCompleted(), todo.isEdit());
        return todo;
    }

    public Todo findById(String id) throws SQLException {
        String sql = "select * from todo_table where id = ?";
        return template.queryForObject(sql, todoRowMapper(), id);

    }

    public void update(String id, String text) throws SQLException {
        String sql = "update todo_table set text=? where id=?";
        template.update(sql, text, id);
    }

    public void delete(String id) throws SQLException {
        String sql = "delete from todo_table where id=?";
        template.update(sql, id);
    }

    private RowMapper<Todo> todoRowMapper() {
        return (rs, rowNum) -> {
            Todo todo = new Todo();
            todo.setId(rs.getString("id"));
            todo.setText(rs.getString("text"));
            todo.setCompleted(rs.getBoolean("isCompleted"));
            todo.setEdit(rs.getBoolean("isEdit"));
            return todo;
        };
    }
}
