package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import UserExercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class UserDaoTest {

    @Test
    @DisplayName("add and findById Test")
    void test01() throws SQLException {
        UserDao_useInterface userDao = new UserDaoFactory().localUserDao();
        User user = new User("1", "Changbum", "1234");
        userDao.add(user);

        User findUser = userDao.findById("1");
        assertEquals("Changbum", findUser.getName());
    }
}