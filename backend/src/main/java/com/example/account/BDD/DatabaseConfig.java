//package com.example.account.BDD;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        String url = "jdbc:mysql://localhost:3306/bancodb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//        //String url = "jdbc:mysql://mysql:3306/bancodb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//        String username = "root";
//        String password = "root";
//
//        return DataSourceFactory.createMySQLDataSource(url, username, password);
//    }
//}
