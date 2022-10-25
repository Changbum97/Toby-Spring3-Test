package UserExercise.dao.useInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() {
        String dbHost = "jdbc:mysql://127.0.0.1:3306/likelion-db";
        String dbUser = "root";
        String dbPassword = "root1234";

        try {
            Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
