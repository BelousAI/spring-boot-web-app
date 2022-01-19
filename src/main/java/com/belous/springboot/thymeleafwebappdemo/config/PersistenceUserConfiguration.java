package com.belous.springboot.thymeleafwebappdemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {"${security.spring.data.jpa.repository.packages}"},
        entityManagerFactoryRef = "securityEntityManagerFactory",
        transactionManagerRef = "securityTransactionManager")
public class PersistenceUserConfiguration {

    @Bean
    @ConfigurationProperties(prefix="security.datasource")
    public DataSource securityDataSource () {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="security.spring.data.jpa.entity")
    public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("securityDataSource") DataSource securityDataSource) {

        return builder
                .dataSource(securityDataSource)
                .build();
    }

    @Bean
    public PlatformTransactionManager securityTransactionManager(
            @Qualifier("securityEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean securityEntityManagerFactory) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(securityEntityManagerFactory.getObject());
        return transactionManager;
    }

    // solving the problem with circular bean dependencies
    // bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
