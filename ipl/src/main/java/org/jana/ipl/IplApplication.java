package org.jana.ipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = {"org.jana.ipl.repo"})
public class IplApplication {

    public static void main(String[] args) {
        SpringApplication.run(IplApplication.class, args);
    }

}
