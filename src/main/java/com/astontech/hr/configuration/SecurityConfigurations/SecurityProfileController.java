package com.astontech.hr.configuration.SecurityConfigurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;

@Controller
public class SecurityProfileController {

    private SecurityProfileService securityProfileService;

    public SecurityProfileService getSecurityProfileService() {
        return securityProfileService;
    }

    @Autowired
    public void setSecurityProfileService(SecurityProfileService securityProfileService) {
        this.securityProfileService = securityProfileService;
    }

    public void setMethod(AuthenticationManagerBuilder auth) throws Exception {
        securityProfileService.AuthorizationMethod(auth);
    }

    public void setPaths(HttpSecurity httpSecurity) throws Exception {
        securityProfileService.HttpPathAuthorization(httpSecurity);
    }
}
