package com.spring.orm.topic02_session_factory_config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * Topic    : Wiring Hibernate into Spring - the bean chain
 * Key idea : DataSource -> LocalSessionFactoryBean -> SessionFactory -> HibernateTransactionManager
 *              DataSource          : connections (same as spring-jdbc)
 *              SessionFactory      : Hibernate's engine; knows your @Entity classes
 *              TransactionManager  : lets @Transactional begin/commit/rollback Hibernate sessions
 *            hibernate-config.xml in resources is the SAME chain in XML (what the original
 *            tutorial used); compare them side by side.
 *            The package is still called "hibernate5", but Spring 6.2 runs it on Hibernate 6.
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    public static final String ENTITY_PACKAGE = "com.spring.orm.topic01_entity_mapping";

    @Bean
    public DataSource dataSource(Environment env) {
        return DataSources.fromEnvironment(env);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(ENTITY_PACKAGE);          // find the @Entity classes
        factory.setHibernateProperties(hibernateProperties());
        return factory;                                     // Spring turns this into a SessionFactory bean
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    static Properties hibernateProperties() {
        Properties props = new Properties();
        // create the tables from the entities at startup, drop them at shutdown (learning only;
        // real apps use "validate" plus a migration tool such as Flyway)
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        props.setProperty("hibernate.show_sql", System.getProperty("show.sql", "false"));
        // lets topic04 count how many SQL statements Hibernate really sent
        props.setProperty("hibernate.generate_statistics", "true");
        // no hibernate.dialect needed: Hibernate 6 detects the database itself
        return props;
    }
}
