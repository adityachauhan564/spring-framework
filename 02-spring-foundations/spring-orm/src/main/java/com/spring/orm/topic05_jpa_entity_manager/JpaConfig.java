package com.spring.orm.topic05_jpa_entity_manager;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.spring.orm.topic02_session_factory_config.DataSources;
import com.spring.orm.topic02_session_factory_config.HibernateConfig;

/*
 * Topic    : JPA - the standard API, with Hibernate underneath
 * Key idea : JPA (jakarta.persistence) is a specification - a rule book that says how an ORM should work.
 *            Hibernate is one implementation of that rule book.
 *            - Like a driving licence: the rules are the same, whichever car brand you drive.
 *            - Code written with EntityManager works with any JPA provider, not only Hibernate.
 *            The chain is the same as topic02, only with JPA names:
 *              SessionFactory         -> EntityManagerFactory (LocalContainerEntityManagerFactoryBean)
 *              Session                -> EntityManager
 *              HibernateTransactionManager -> JpaTransactionManager
 *            Spring Data JPA and Spring Boot (stage 03) are built on exactly this.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
public class JpaConfig {

    @Bean
    public DataSource dataSource(Environment env) {
        return DataSources.fromEnvironment(env);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(HibernateConfig.ENTITY_PACKAGE);   // the same Student entity as before
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // "use Hibernate as the JPA provider"
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        factory.setJpaProperties(props);
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager(jakarta.persistence.EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
