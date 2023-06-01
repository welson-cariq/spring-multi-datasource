package com.example.multi.datasource.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.multi.datasource.repository.transaction",
        entityManagerFactoryRef = "trxEntityManager",
        transactionManagerRef = "trxTransactionManager"
)
public class TransactionDatabaseConfig extends DatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.trx-datasource")
    public DataSource trxDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean trxEntityManager(@Qualifier("trxDataSource") DataSource dataSource) {
        return configEntityManager(dataSource, "com.example.multi.datasource.model.transaction");
    }

    @Bean
    public PlatformTransactionManager trxTransactionManager(
            EntityManagerFactory trxEntityManager) {
        return new JpaTransactionManager(trxEntityManager);
    }
}
