package springmvc.topic04_service_and_dao_layers;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * The same chain as spring-orm topic02: DataSource -> SessionFactory -> transaction manager.
 * Database: in-memory H2 by default (all users are lost when the server stops), or MySQL with
 *   DB_URL=jdbc:mysql://localhost:3306/springmvc?createDatabaseIfNotExist=true
 *   plus DB_USERNAME / DB_PASSWORD in the environment the server runs in.
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @Bean
    public DataSource dataSource(Environment env) {
        String url = env.getProperty("DB_URL", "");
        if (url.isBlank()) {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).generateUniqueName(true).build();
        }
        return new DriverManagerDataSource(url, env.getProperty("DB_USERNAME", "root"), env.getProperty("DB_PASSWORD", ""));
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Environment env) {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(User.class.getPackageName());
        Properties props = new Properties();
        // in-memory: build fresh tables on every start. A real database: keep the data between runs
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("DB_URL", "").isBlank() ? "create-drop" : "update");
        factory.setHibernateProperties(props);
        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
