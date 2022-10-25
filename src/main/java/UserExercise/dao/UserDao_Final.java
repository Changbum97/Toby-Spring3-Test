package UserExercise.dao;

import UserExercise.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao_Final {
    private final JdbcTemplate jdbcTemplate;

    public UserDao_Final(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM users;");
    }

    public void add(User user) {
        this.jdbcTemplate.update("INSERT INTO users(id, name, password) VALUES (?,?,?);",
                user.getId(), user.getName(), user.getPassword());
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users;", Integer.class);
    }

    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            return user;
        }
    };

    public User findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?;";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users ORDER BY id;";
        return this.jdbcTemplate.query(sql, rowMapper);
    }
}
