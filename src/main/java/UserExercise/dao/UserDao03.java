package UserExercise.dao;

import UserExercise.dao.useInterface.ConnectionMaker;
import UserExercise.dao.useStrategy.*;
import UserExercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao03 {
    ConnectionMaker connectionMaker;

    public UserDao03(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectionMaker.makeConnection();

            ps = stmt.makePreparedStatement(conn);

            // 여기서 executeUpdate 고정이기 때문에 add, deleteAll만 가능
            ps.executeUpdate();

            System.out.println("쿼리 실행 완료");

        } catch (SQLException e) {
            throw new SQLException("쿼리 실행 실패");
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void deleteAll() throws SQLException {
        jdbcContextWithStatementStrategy(new DeleteAllStrategy());
    }

    public void add(User user) throws SQLException {
        jdbcContextWithStatementStrategy(new AddStrategy(user));
    }

}
