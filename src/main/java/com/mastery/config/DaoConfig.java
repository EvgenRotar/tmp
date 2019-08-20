package com.mastery.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DaoConfig {

  private final DataSource dataSource;

  @Autowired
  public DaoConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @PostConstruct
  protected void initialize() {
    Resource createScript = new ClassPathResource("create-table.sql");
    DatabasePopulator databasePopulator = new ResourceDatabasePopulator(createScript);
    DatabasePopulatorUtils.execute(databasePopulator, dataSource);

    Resource insertScript = new ClassPathResource("insert-values.sql");
    databasePopulator = new ResourceDatabasePopulator(insertScript);
    DatabasePopulatorUtils.execute(databasePopulator, dataSource);
  }

  @Bean
  public PlatformTransactionManager txManager() {
    return new DataSourceTransactionManager(dataSource);
  }

}