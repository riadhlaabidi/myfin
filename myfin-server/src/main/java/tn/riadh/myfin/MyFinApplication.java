package tn.riadh.myfin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.liquibase.autoconfigure.LiquibaseProperties;

@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class })
public class MyFinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFinApplication.class, args);
    }

}
