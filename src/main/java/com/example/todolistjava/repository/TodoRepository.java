package com.example.todolistjava.repository;

import com.example.todolistjava.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
@Slf4j
public class TodoRepository {

    private final DataSource dataSource;

    public TodoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Todo todo) throws SQLException {
        String sql = "insert into todo_table(id, text, isCompleted, isEdit) values (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, todo.getId());
            pstmt.setString(2, todo.getText());
            pstmt.setBoolean(3, todo.isCompleted());
            pstmt.setBoolean(4, todo.isEdit());
            pstmt.executeUpdate();

        }catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }

    public Todo findById(String id) throws SQLException {
        String sql = "select * from todo_table where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                Todo todo = new Todo();
                todo.setId(rs.getString("id"));
                todo.setText(rs.getString("text"));
                todo.setCompleted(rs.getBoolean("isCompleted"));
                todo.setEdit(rs.getBoolean("isEdit"));
                return todo;
            } else {
                throw new NoSuchElementException("todo not found id=" + id);
            }

        }catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public void update(String id, String text) throws SQLException {
        String sql = "update todo_table set text=? where id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, text);
            pstmt.setString(2, id);

            pstmt.executeUpdate();

        }catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "delete from todo_table where id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            pstmt.executeUpdate();

        }catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }
    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeConnection(con);
    }

    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        return con;
    }

    public List<Todo> findAll() {
        return null;
    }
}
