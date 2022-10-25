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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory_useSpring.class)
class UserDaoTest_useSpring {

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("add, findById, deleteAll, getCount Test")
    void test() throws SQLException {
        // UserDao_useInterface userDao = context.getBean("localUserDao_useInterface", UserDao_useInterface.class);

        // Statement Strategy 적용
        UserDao_useStatementStrategy userDao = context.getBean("localUserDao_useStatementStrategy", UserDao_useStatementStrategy.class);

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
        // UserDao_useInterface userDao = context.getBean("localUserDao_useInterface", UserDao_useInterface.class);
        UserDao_useStatementStrategy userDao = context.getBean("localUserDao_useStatementStrategy", UserDao_useStatementStrategy.class);
        userDao.deleteAll();

        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.findById("1");
        });
    }

    @Test
    @DisplayName("find All Test")
    void findAllTest() throws SQLException {
        UserDao_useStatementStrategy userDao = context.getBean("localUserDao_useStatementStrategy", UserDao_useStatementStrategy.class);
        userDao.deleteAll();

        User userA = new User("1", "AAA", "1234");
        User userB = new User("2", "BBB", "4321");
        User userC = new User("3", "CCC", "1111");

        userDao.add(userA);
        userDao.add(userB);
        userDao.add(userC);

        List<User> users = userDao.findAll();

        assertEquals(3, users.size());

        // User의 eqauls Method Overriding
        assertEquals(userA, users.get(0));
        assertEquals(userB, users.get(1));
        assertEquals(userC, users.get(2));
    }
}