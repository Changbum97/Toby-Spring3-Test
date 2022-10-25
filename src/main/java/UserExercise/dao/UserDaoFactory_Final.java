package UserExercise.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class UserDaoFactory_Final {

    @Bean
    public UserDao_Final localUserDao() {
        return new UserDao_Final(this.localDataSource());
    }

    @Bean
    public DataSource localDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/likelion-db");
        dataSource.setUsername("root");
        dataSource.setPassword("root1234");
        return dataSource;
    }
}
