package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory04 {

    @Bean
    public UserDao03 localUserDao() {
        return new UserDao03(new LocalConnectionMaker());
    }

}
