package UserExercise.dao;

import UserExercise.dao.useInterface.ConnectionMaker;
import UserExercise.domain.User;

import java.sql.*;

public class UserDao_useInterface {
    ConnectionMaker connectionMaker;

    public UserDao_useInterface(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
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
            System.out.println("Insert문 생성 실패");
            throw new SQLException();
        }

        try {
            ps.executeUpdate();
            System.out.println("DB Insert 완료");

            ps.close();
            conn.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("id 중복");
            throw new SQLIntegrityConstraintViolationException();
        } catch (SQLException e) {
            System.out.println("Inset 실패");
            throw new SQLException();
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
                System.out.println("Id에 해당하는 User 없음");
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
