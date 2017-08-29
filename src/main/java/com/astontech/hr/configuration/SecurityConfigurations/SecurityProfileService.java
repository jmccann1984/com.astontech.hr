package com.astontech.hr.configuration.SecurityConfigurations;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

public interface SecurityProfileService {
    public void AuthorizationMethod(AuthenticationManagerBuilder auth) throws Exception;
    public void HttpPathAuthorization(HttpSecurity httpSecurity) throws Exception;
}
