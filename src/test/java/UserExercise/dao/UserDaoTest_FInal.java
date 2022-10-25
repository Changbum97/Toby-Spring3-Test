package UserExercise.dao;

import UserExercise.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory_Final.class)
class UserDaoTest_FInal {

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("Test All")
    void test() throws SQLException {
        UserDao_Final userDao = context.getBean("localUserDao", UserDao_Final.class);

        userDao.deleteAll();

        User user = new User("1", "changbum", "1111");

        userDao.add(user);
        userDao.add(new User("2", "AAA", "1234"));
        userDao.add(new User("3", "BBB", "4321"));

        assertEquals(3, userDao.getCount());

        assertEquals("changbum", userDao.findById("1").getName());

        assertEquals(3, userDao.findAll().size());

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
    }

}