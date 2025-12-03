package tn.riadh.myfin.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.liquibase.autoconfigure.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfiguration {

    @Bean
    public SpringLiquibase liquibase(
            LiquibaseProperties liquibaseProperties,
            DataSource dataSource) {

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/db/changelog-master.yaml");
        liquibase.setTag(liquibaseProperties.getTag());

        List<String> contexts = liquibaseProperties.getContexts();
        if (contexts != null) {
            liquibase.setContexts(String.join(",", contexts));
        }

        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setLiquibaseSchema(liquibaseProperties.getLiquibaseSchema());
        liquibase.setLiquibaseTablespace(liquibaseProperties.getLiquibaseTablespace());
        liquibase.setDatabaseChangeLogTable(liquibaseProperties.getDatabaseChangeLogTable());
        liquibase.setDatabaseChangeLogLockTable(liquibaseProperties.getDatabaseChangeLogLockTable());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());

        List<String> labels = liquibaseProperties.getLabelFilter();
        if (labels != null) {
            liquibase.setLabelFilter(String.join(",", labels));
        }

        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        liquibase.setClearCheckSums(liquibaseProperties.isClearChecksums());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        liquibase.setRollbackFile(liquibaseProperties.getRollbackFile());
        liquibase.setTestRollbackOnUpdate(liquibaseProperties.isTestRollbackOnUpdate());
        return liquibase;
    }
}
