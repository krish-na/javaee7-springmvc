package com.sampleapp.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Collections;

@EnableTransactionManagement
@ComponentScan(basePackages = {"com.sampleapp.db.repository"})
@PropertySource("/db-config.properties")
@Configuration
public class PersistenceConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("com.sampleapp.db.person");
        emf.setDataSource(dataSource);
        emf.setJpaPropertyMap(Collections.singletonMap("hibernate.hbm2ddl.auto", "create-update"));
        emf.setJpaPropertyMap(Collections.singletonMap("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"));
        emf.setJpaVendorAdapter(adapter);
        return emf;
    }

    @Bean
    public DataSource dataSource(Environment env) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(env.getPropertyAsClass("dataSource.driverClass", Driver.class));
        dataSource.setUrl(env.getProperty("dataSource.url").trim());
        dataSource.setUsername(env.getProperty("dataSource.user").trim());
        dataSource.setPassword(env.getProperty("dataSource.password").trim());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}