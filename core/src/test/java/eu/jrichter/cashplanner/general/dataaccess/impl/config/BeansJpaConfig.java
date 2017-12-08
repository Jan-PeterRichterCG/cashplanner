package eu.jrichter.cashplanner.general.dataaccess.impl.config;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.jrichter.cashplanner.general.dataaccess.base.DatabaseMigrator;

/**
 * Java configuration for JPA
 */
@Configuration
public class BeansJpaConfig {

  /*
   * @Inject private EntityManagerFactory entityManagerFactory; // unused
   */
  @Inject
  private DataSource appDataSource;

  @Value("${database.migration.auto}")
  private Boolean enabled;

  @Value("${database.migration.testdata}")
  private Boolean testdata;

  @Value("${database.migration.clean}")
  private Boolean clean;

  @SuppressWarnings("javadoc")
  @Bean
  public DatabaseMigrator getFlyway() {

    DatabaseMigrator migrator = new DatabaseMigrator();
    migrator.setClean(this.clean);
    migrator.setDataSource(this.appDataSource);
    migrator.setEnabled(this.enabled);
    migrator.setTestdata(this.testdata);
    return migrator;

  }

  @SuppressWarnings("javadoc")
  @PostConstruct
  public void migrate() {

    getFlyway().migrate();
  }

}