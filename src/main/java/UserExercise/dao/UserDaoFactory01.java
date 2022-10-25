package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;

public class UserDaoFactory01 {

    public UserDao01 localUserDao() {
        return new UserDao01(new LocalConnectionMaker());
    }
}
