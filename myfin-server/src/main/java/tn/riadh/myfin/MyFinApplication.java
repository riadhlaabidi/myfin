package tn.riadh.myfin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.liquibase.autoconfigure.LiquibaseProperties;
import org.springframework.modulith.Modulithic;

@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class })
@Modulithic(systemName = "Myfin", sharedModules = { "tn.riadh.myfin.shared" })
public class MyFinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFinApplication.class, args);
    }

}
