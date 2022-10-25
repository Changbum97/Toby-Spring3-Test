package UserExercise.dao.useStrategy;

import UserExercise.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByIdStrategy implements StatementStrategy{
    private String id;

    public FindByIdStrategy(String id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE ID = ?;");
        ps.setString(1, id);
        return ps;
    }
}
