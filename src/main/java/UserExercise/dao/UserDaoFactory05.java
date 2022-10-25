package UserExercise.dao;

import UserExercise.dao.useInterface.LocalConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class UserDaoFactory05 {

    @Bean
    public UserDao04 localUserDao() {
        return new UserDao04(this.localDataSource());
    }

    @Bean
    public DataSource localDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/likelion-db");
        dataSource.setUsername("root");
        dataSource.setPassword("root1234");
        return dataSource;
    }
}
