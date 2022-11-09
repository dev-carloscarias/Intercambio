package com.chn.reporteach.configuration;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableConfigurationProperties
@EnableEncryptableProperties
public class DataSourceConfig
{

    private final Environment environment;


    public DataSourceConfig(Environment environment )
    {
        this.environment = environment;
    }

    @Primary
    @Bean(name = "spring")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(this.environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(this.environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(this.environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "second")
    public DataSourceProperties secondProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "Second")
    public DataSource getDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("second.datasource.driver-class-name")));
        dataSource.setUrl(this.environment.getProperty("second.datasource.url"));
        dataSource.setUsername(this.environment.getProperty("second.datasource.username"));
        dataSource.setPassword(this.environment.getProperty("second.datasource.password"));
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "third")
    public DataSourceProperties thirdProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "Third")
    public DataSource getDataSource3() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("third.datasource.driver-class-name")));
        dataSource.setUrl(this.environment.getProperty("third.datasource.url"));
        dataSource.setUsername(this.environment.getProperty("third.datasource.username"));
        dataSource.setPassword(this.environment.getProperty("third.datasource.password"));
        return dataSource;
    }
}
