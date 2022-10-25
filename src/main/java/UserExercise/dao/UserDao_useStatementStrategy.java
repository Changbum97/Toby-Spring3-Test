package UserExercise.dao;

import UserExercise.dao.useInterface.ConnectionMaker;
import UserExercise.dao.useStrategy.AddStrategy;
import UserExercise.dao.useStrategy.DeleteAllStrategy;
import UserExercise.dao.useStrategy.FindByIdStrategy;
import UserExercise.dao.useStrategy.GetCountStrategy;
import UserExercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;

public class UserDao_useStatementStrategy {
    ConnectionMaker connectionMaker;

    public UserDao_useStatementStrategy(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void deleteAll() throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = null;

        try {
            ps = new DeleteAllStrategy().makePreparedStatement(conn);

            ps.executeUpdate();
            System.out.println("Delete All 성공");

            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new SQLException("Delete All 실패");
        }

    }

    public void add(User user) throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = null;

        try {
            ps = new AddStrategy(user).makePreparedStatement(conn);

        } catch (SQLException e) {
            throw new SQLException("Insert문 생성 실패");
        }

        try {
            ps.executeUpdate();
            System.out.println("Insert 성공");

            ps.close();
            conn.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("ID 중복");
        } catch (SQLException e) {
            throw new SQLException("Insert 실패");
        }
    }

    public int getCount() throws SQLException {
        Connection conn = connectionMaker.makeConnection();
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

    public User findById(String id) throws SQLException, EmptyResultDataAccessException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = null;

        try {
            ps = new FindByIdStrategy(id).makePreparedStatement(conn);

        } catch (SQLException e) {
            System.out.println("SELECT문 생성 실패");
            throw new SQLException();
        }

        try {
            ResultSet rs = ps.executeQuery();
            User user = null;

            if (rs.next()) {
                System.out.println("FindById 완료");
                user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            } else {
                System.out.println("해당 User 없음");
                throw new EmptyResultDataAccessException(1);
            }

            rs.close();
            ps.close();
            conn.close();

            return user;

        } catch (SQLException e) {
            System.out.println("FindById 실패");
            throw new SQLException();
        }
    }
}
