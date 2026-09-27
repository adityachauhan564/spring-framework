package com.spring.orm.topic02_session_factory_config;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/*
 * Same rule as spring-jdbc: in-memory H2 unless DB_URL is set
 * (e.g. DB_URL=jdbc:mysql://localhost:3306/springorm?createDatabaseIfNotExist=true,
 * plus DB_USERNAME / DB_PASSWORD).
 */
public final class DataSources {

    private DataSources() {
    }

    public static DataSource fromEnvironment(Environment env) {
        String url = env.getProperty("DB_URL", "");
        if (url.isBlank()) {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).generateUniqueName(true).build();
        }
        return new DriverManagerDataSource(url, env.getProperty("DB_USERNAME", "root"), env.getProperty("DB_PASSWORD", ""));
    }
}
