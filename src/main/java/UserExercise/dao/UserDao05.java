package UserExercise.dao;

import UserExercise.dao.useStrategy.*;
import UserExercise.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao05 {

    DataSource dataSource;
    JdbcContext jdbcContext;

    public UserDao05(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(dataSource);
    }

    public void deleteAll() {
        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("delete from users");
            }
        });
    }

    public void add(User user) {
        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) values (?, ?, ?);");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });
    }

    public int getCount() throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = null;

        try {
            ps = new GetCountStrategy().makePreparedStatement(conn);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int cnt = rs.getInt(1);
            System.out.println("Get Count 성공");

            rs.close();
            ps.close();
            conn.close();

            return cnt;

        } catch (SQLException e) {
            throw new SQLException("Get Count 실패");
        }
    }

}
