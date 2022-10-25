package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;

public class UserDaoFactory {

    public UserDao_useInterface localUserDao() {
        return new UserDao_useInterface(new LocalConnectionMaker());
    }
}
