package com.foobar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "myFooEntityManagerFactory",
        transactionManagerRef = "myFooTransactionManager", basePackages = {"com.foobar.mysql.repo"})
public class MyFooDbConfig {
    @Value("${mysql.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Bean(name = "myFooDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "myFooEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("myFooDataSource") DataSource dataSource) {

        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", hibernateDialect);

        return builder.dataSource(dataSource)
                .properties(props)
                .packages("com.foobar.mysql.domain")
                .persistenceUnit("foo")
                .build();
    }

    @Bean(name = "myFooTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("myFooEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

}
