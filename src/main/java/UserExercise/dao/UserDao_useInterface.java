package UserExercise.dao;

import UserExercise.dao.useInterface.ConnectionMaker;
import UserExercise.domain.User;

import java.sql.*;

public class UserDao_useInterface {
    ConnectionMaker connectionMaker;

    public UserDao_useInterface(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void deleteAll() throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM users");

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
            ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?);");

            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

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
            ps = conn.prepareStatement("SELECT COUNT(*) FROM users");


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

    public User findById(String id) throws SQLException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?;");
            ps.setString(1, id);
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
                throw new SQLException("Id에 해당하는 User 없음");
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
