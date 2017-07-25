package com.astontech.hr.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.astontech.hr.domain"})
@EnableJpaRepositories(basePackages = {"com.astontech.hr.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
