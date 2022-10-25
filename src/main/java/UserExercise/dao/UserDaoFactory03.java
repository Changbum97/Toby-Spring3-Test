package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory03 {

    @Bean
    public UserDao02 localUserDao() {
        return new UserDao02(new LocalConnectionMaker());
    }

}
