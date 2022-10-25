package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class UserDaoFactory {

    public UserDao_useInterface localUserDao() {
        return new UserDao_useInterface(new LocalConnectionMaker());
    }
}
