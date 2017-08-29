package com.astontech.hr.configuration.SecurityConfigurations;

import org.springframework.context.annotation.Profile;
import org.springframework.security.access.method.P;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
//@Profile("none")
public class SecurityProfileNone implements SecurityProfileService {

    @Override
    public void AuthorizationMethod(AuthenticationManagerBuilder auth) throws Exception {

    }

    @Override
    public void HttpPathAuthorization(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                permit all with no authentication
                    .authorizeRequests().antMatchers("/").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/console/**").permitAll();
    }
}
