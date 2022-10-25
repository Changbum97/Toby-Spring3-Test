package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory_useSpring {

    @Bean
    public UserDao_useInterface localUserDao_useInterface() {
        return new UserDao_useInterface(new LocalConnectionMaker());
    }

    @Bean
    public UserDao_useStatementStrategy localUserDao_useStatementStrategy() {
        return new UserDao_useStatementStrategy(new LocalConnectionMaker());
    }

}
