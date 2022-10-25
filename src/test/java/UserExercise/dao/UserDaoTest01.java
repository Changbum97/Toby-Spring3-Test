package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import UserExercise.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UserDaoTest01 {

    @Test
    @DisplayName("add, findById, deleteAll, getCount Test")
    void test() throws SQLException {
        // Interface 사용
        UserDao01 userDao = new UserDao01(new LocalConnectionMaker());

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

}