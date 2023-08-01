package com.example.todolistjava.repository;

import com.example.todolistjava.connection.DBConnectionUtil;
import com.example.todolistjava.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
@Slf4j
public class TodoRepository {

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

    private void close(Connection con, Statement stmt, ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    public List<Todo> findAll() {
        return null;
    }
}
