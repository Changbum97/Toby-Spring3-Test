package UserExercise.dao;

import UserExercise.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory04.class)
class UserDaoTest05 {

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("add, deleteAll, getCount Test")
    void test() throws SQLException {
        UserDao03 userDao = context.getBean("localUserDao", UserDao03.class);

        userDao.deleteAll();

        userDao.add(new User("1", "AAA", "1234"));
        userDao.add(new User("2", "BBB", "4321"));

        assertEquals(2, userDao.getCount());

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
        assertEquals(0, userDao.getCount());
    }

}