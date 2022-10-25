package UserExercise.dao.useStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetCountStrategy implements StatementStrategy{

    @Override
    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT COUNT(*) FROM users;");
    }
}
