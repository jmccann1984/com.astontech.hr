package com.astontech.hr;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */

@SpringBootApplication
public class Application {

//    @Value("${spring.datasource.url}")
//    String datasourceUrl;
//
//    @Value("${spring.datasource.username}")
//    String datasourceUsername;
//
//    @Value("${spring.datasource.password}")
//    String datasourcePassword;
//
//    @Value("${spring.datasource.driverClassName}")
//    String datasourceDriverClassName;

//    @Bean
//    public DataSource dataSourceI() {
//
//        DataSource ds = new DataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/hr_app");
//        ds.setUsername("conUser");
//        ds.setPassword("qwe123$!");
//
//        return ds;
//    }

    public static void main(String[] args) { SpringApplication.run(Application.class, args); }
}
