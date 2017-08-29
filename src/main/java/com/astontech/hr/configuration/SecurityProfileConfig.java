//package com.astontech.hr.configuration;
//
//import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileFactory;
//import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//public class SecurityProfileConfig {
//
//    @Bean
//    public SecurityProfileFactory securityConfigurationFactory(){
//        return new SecurityProfileFactory();
//    }
//
//    @Bean
//    public SecurityProfileService setLdapSecurity(SecurityProfileFactory factory){
//        return factory.createSecurityConfigurationService("ldap");
//    }
//
//    @Bean
//    @Profile("none")
//    @Primary
//    public SecurityProfileService setNoSecurity(SecurityProfileFactory factory){
//        return factory.createSecurityConfigurationService("none");
//    }
//
//    @Bean
//    @Profile("inMemory")
//    @Primary
//    public SecurityProfileService setInMemorySecurity(SecurityProfileFactory factory){
//        return factory.createSecurityConfigurationService("inMemory");
//    }
//
//    @Bean
//    @Profile("database")
//    @Primary
//    public SecurityProfileService setDatabaseSecurity(SecurityProfileFactory factory){
//        return factory.createSecurityConfigurationService("database");
//    }
//}
