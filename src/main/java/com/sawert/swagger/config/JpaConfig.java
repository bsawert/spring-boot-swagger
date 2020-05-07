package com.sawert.swagger.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

//@Configuration
@Profile("bob") // Only activate this in the "prod" profile
public class JpaConfig {
    //@Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.mariadb.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mariadb://localhost:3306/test");
        dataSourceBuilder.username("bsawert");
        dataSourceBuilder.password("nopass");
        return dataSourceBuilder.build();
    }
}
