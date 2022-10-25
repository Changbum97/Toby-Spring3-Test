package UserExercise.dao;

import UserExercise.dao.useInterface.ConnectionMaker;
import UserExercise.dao.useStrategy.AddStrategy;
import UserExercise.dao.useStrategy.DeleteAllStrategy;
import UserExercise.dao.useStrategy.StatementStrategy;
import UserExercise.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao04 {

    // ConnectionMaker가 아닌 DataSource 사용
    DataSource dataSource;

    public UserDao04(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();

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
