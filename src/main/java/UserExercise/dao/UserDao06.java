package UserExercise.dao;

import UserExercise.dao.useStrategy.GetCountStrategy;
import UserExercise.dao.useStrategy.StatementStrategy;
import UserExercise.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao06 {

    DataSource dataSource;
    JdbcContext jdbcContext;

    public UserDao06(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(dataSource);
    }

    public void deleteAll() {
        jdbcContext.executeSql("DELETE FROM users;");
    }

    public void add(User user) {
        String sql = "INSERT INTO users(id, name, password) values (\"";
        sql += user.getId() + "\",\"";
        sql += user.getName() + "\",\"";
        sql += user.getPassword() + "\");";
        System.out.println(sql);
        jdbcContext.executeSql(sql);
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
