package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory02 {

    @Bean
    public UserDao01 localUserDao() {
        return new UserDao01(new LocalConnectionMaker());
    }
}
