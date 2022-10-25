package UserExercise.dao;

import UserExercise.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UserDaoTest02 {

    @Test
    @DisplayName("add, findById, deleteAll, getCount Test")
    void test() throws SQLException {
        // Factory 사용
        UserDao01 userDao = new UserDaoFactory().localUserDao();

        // userDao Test 시작
        userDao.deleteAll();

        User user = new User("1", "Changbum", "1234");
        userDao.add(user);

        // add, getCount Test
        assertEquals(1, userDao.getCount());

        // findById Test
        User findUser = userDao.findById("1");
        assertEquals("Changbum", findUser.getName());

        userDao.add(new User("2", "BBB", "4321"));
        userDao.add(new User("3", "CCC", "1111"));

        assertEquals(3, userDao.getCount());

        // delteAll Test
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
    }

    @Test
    @DisplayName("user == null인 경우 Exception 발생 Test")
    void exceptionTest() throws SQLException {
        UserDao01 userDao = new UserDaoFactory().localUserDao();
        userDao.deleteAll();

        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.findById("1");
        });
    }
}