package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
public class TodoRepository {

    private final NamedParameterJdbcTemplate template;
    private static final String saveSql = "insert into todo_table(id, text, isCompleted, isEdit) values (:id, :text, :isCompleted, :isEdit)";
    private static final String findSql = "select * from todo_table where id=:id";
    private static final String updateSql = "update todo_table set text=:text where id=:id";
    private static final String deleteSql = "delete from todo_table where id=:id";

    public TodoRepository(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    public Todo save(Todo todo) throws SQLException {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", todo.getId())
                .addValue("text", todo.getText())
                .addValue("isCompleted", todo.isCompleted())
                .addValue("isEdit", todo.isEdit());

        template.update(saveSql, param);
        return todo;
    }

    public Todo findById(String id) throws SQLException {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(findSql, param, todoRowMapper());

    }

    public void update(String id, String text) throws SQLException {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("text", text)
                .addValue("id", id);
        template.update(updateSql, param);
    }

    public void delete(String id) throws SQLException {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(deleteSql, param);
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
