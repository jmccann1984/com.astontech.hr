package com.astontech.hr.configuration.SecurityConfigurations;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
@Profile("inMemory")
public class SecurityProfileInMemory implements SecurityProfileService{
    @Override
    public void AuthorizationMethod(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("qwe123$!").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("qwe123$!").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("qwe123$!").roles("DBA");
    }

    @Override
    public void HttpPathAuthorization(HttpSecurity httpSecurity) throws Exception {
         httpSecurity

//                create authentication for ADMIN and anything with the URL=/admin/**
                    .authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .and()
                    .authorizeRequests().antMatchers("/console/**").access("hasRole('ROLE_DBA')");

    }
}
