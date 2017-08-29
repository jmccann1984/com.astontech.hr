package com.astontech.hr;

//import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileController;
import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileLDAP;
import com.astontech.hr.configuration.SecurityConfigurations.SecurityProfileService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        for( String bean : ctx.getBeanDefinitionNames()) {
            String beanS = ctx.getBean(bean).toString();
            if(beanS.contains("ecurity")) {
                System.out.println(beanS);
            }
        }
//        SecurityProfileService securityProfileService = (SecurityProfileService) ctx.getBean("securityProfileService");
//        if(securityProfileService instanceof SecurityProfileService) {
//            System.out.println("got the bean");
//        }

    }
}
