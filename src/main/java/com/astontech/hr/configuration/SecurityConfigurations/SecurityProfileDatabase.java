package com.astontech.hr.configuration.SecurityConfigurations;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("database")
public class SecurityProfileDatabase implements SecurityProfileService {

    DataSource dataSource;

    @Value("${spring.datasource.url}")
    String datasourceUrl;

    @Value("${spring.datasource.username}")
    String datasourceUsername;

    @Value("${spring.datasource.password}")
    String datasourcePassword;

    @Value("${spring.datasource.driverClassName}")
    String datasourceDriverClassName;

    @Override
    public void AuthorizationMethod(AuthenticationManagerBuilder auth) throws Exception {

        dataSource = dataSourceI();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
            userDetailsManager.setDataSource(dataSource);
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            auth.userDetailsService(userDetailsManager).passwordEncoder(encoder);
            auth.jdbcAuthentication().dataSource(dataSource);

            if(!userDetailsManager.userExists("user")) {
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("USER"));
                User userDetails = new User("user", encoder.encode("password"), authorities);

                userDetailsManager.createUser(userDetails);}

    }

    @Override
    public void HttpPathAuthorization(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .authorizeRequests().antMatchers("/static/**").permitAll()
            .and()
            .authorizeRequests().antMatchers("/login**").permitAll()
            .and()
            .authorizeRequests().antMatchers("/admin/**").hasAuthority("USER")
            .anyRequest().authenticated();

    }


    public DataSource dataSourceI() {
        DataSource ds = new DataSource();
        ds.setDriverClassName(datasourceDriverClassName);
        ds.setUrl(datasourceUrl);
        ds.setUsername(datasourceUsername);
        ds.setPassword(datasourcePassword);
        return ds;
    }
}
