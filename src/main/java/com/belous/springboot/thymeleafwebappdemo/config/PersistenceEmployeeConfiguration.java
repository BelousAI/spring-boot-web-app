package com.belous.springboot.thymeleafwebappdemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"${app.spring.data.jpa.repository.packages}"},
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager")
public class PersistenceEmployeeConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="app.datasource")
    public DataSource appDataSource () {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix="app.spring.data.jpa.entity")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("appDataSource") DataSource appDataSource) {

        return builder
                .dataSource(appDataSource)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager appTransactionManager
            (@Qualifier("appEntityManagerFactory") LocalContainerEntityManagerFactoryBean appEntityManagerFactory) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(appEntityManagerFactory.getObject());
        return transactionManager;
    }
}
