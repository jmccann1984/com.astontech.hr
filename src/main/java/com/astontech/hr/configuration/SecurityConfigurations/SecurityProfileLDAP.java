package com.astontech.hr.configuration.SecurityConfigurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
//@Profile("ldap")
public class SecurityProfileLDAP implements SecurityProfileService {

    @Value("${spring.security.ldap.domain}")
    private String ldapDomain;

    @Value("${spring.security.ldap.url}")
    private String ldapUrl;

    @Override
    public void AuthorizationMethod(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
    }

    @Override
    public void HttpPathAuthorization(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests().antMatchers("/static/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/login**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
    }

    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider authProvider =
                new ActiveDirectoryLdapAuthenticationProvider(ldapDomain, ldapUrl);

        authProvider.setConvertSubErrorCodesToExceptions(true);
        authProvider.setUseAuthenticationRequestCredentials(true);

        return authProvider;
    }
}
