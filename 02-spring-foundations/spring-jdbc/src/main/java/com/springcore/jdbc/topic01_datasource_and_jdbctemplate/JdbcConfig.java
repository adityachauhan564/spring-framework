package com.springcore.jdbc.topic01_datasource_and_jdbctemplate;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/*
 * Topic    : DataSource and JdbcTemplate - the setup every Spring JDBC app needs
 * Key idea : DataSource   = the place where database connections come from (which database, which user).
 *            JdbcTemplate = runs your SQL on that DataSource, and does the boring JDBC work for you
 *                           (open connection, create statement, close everything, handle errors).
 *            - Like an ATM: the DataSource is the bank's network, JdbcTemplate is the machine
 *              that does all the steps - you only say what you want.
 *            - Every other topic in this project reuses this class.
 *
 * Which database:
 *   default          -> an in-memory H2 database (nothing to install; it starts empty on every run)
 *   DB_URL is set    -> that database, e.g. DB_URL=jdbc:mysql://localhost:3306/springjdbc?createDatabaseIfNotExist=true
 *                       with DB_USERNAME (default root) and DB_PASSWORD. Never commit passwords to git.
 */
@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource(Environment env) {
        String url = env.getProperty("DB_URL", "");
        if (url.isBlank()) {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .generateUniqueName(true)          // every container (and every test) gets its own separate DB
                    .build();
        }
        // DriverManagerDataSource opens a brand-new connection every time: fine for learning.
        // Real applications use a connection pool (a set of ready connections that are reused),
        // such as HikariCP - Spring Boot's default. Like auto-rickshaws waiting at a stand vs calling a new one each time.
        return new DriverManagerDataSource(url, env.getProperty("DB_USERNAME", "root"), env.getProperty("DB_PASSWORD", ""));
    }

    // runs db/schema.sql at startup, so the tables always exist
    @Bean
    public DataSourceInitializer schemaInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("db/schema.sql")));
        return initializer;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
