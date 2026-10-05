package com.springcore.jdbc.topic05_transactions;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcConfig;

/*
 * Two things are needed to switch transactions on:
 *   @EnableTransactionManagement - wraps @Transactional beans in a transaction proxy
 *   a PlatformTransactionManager  - knows HOW to begin / commit / roll back. For plain JDBC
 *                                   that is DataSourceTransactionManager (spring-orm uses
 *                                   HibernateTransactionManager / JpaTransactionManager)
 */
@Configuration
@EnableTransactionManagement
@Import(JdbcConfig.class)
@ComponentScan
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
