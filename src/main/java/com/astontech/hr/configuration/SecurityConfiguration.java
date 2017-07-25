package com.astontech.hr.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //permit all with no authentication
        httpSecurity
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}
