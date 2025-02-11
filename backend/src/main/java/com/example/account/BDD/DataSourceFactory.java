//package com.example.account.BDD;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import javax.sql.DataSource;
//
//public class DataSourceFactory {
//
//    public static DataSource createMySQLDataSource(String url, String username, String password) {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(url);
//        config.setUsername(username);
//        config.setPassword(password);
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.setMaximumPoolSize(10);
//        config.setMinimumIdle(2);
//        config.setIdleTimeout(30000);
//        config.setConnectionTimeout(30000);
//        config.setMaxLifetime(1800000);
//
//        return new HikariDataSource(config);
//    }
//}
