package com.astontech.hr.configuration;

import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileController;
import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileFactory;
import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

//    @Value("${spring.security.authentication.method}")
//    private String authenticationMethod;

    @Autowired
    private SecurityProfileService securityProfile;


//    @Autowired
//    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        securityProfile.AuthorizationMethod(auth);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//      region ACCESS CONTROL

        securityProfile.HttpPathAuthorization(httpSecurity);

//      endregion

//      region COMMON SECURITY CONFIGURATION
        httpSecurity
                .formLogin().loginPage("/login").loginProcessingUrl("/login.do/")
                .defaultSuccessUrl("/", true).failureUrl("/login?err=1")
                .usernameParameter("username").passwordParameter("password");

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

//      endregion
    }



}
