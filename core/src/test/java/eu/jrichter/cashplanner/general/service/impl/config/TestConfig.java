package eu.jrichter.cashplanner.general.service.impl.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import eu.jrichter.cashplanner.general.common.DbTestHelper;
import eu.jrichter.cashplanner.general.common.RestTestClientBuilder;
import eu.jrichter.cashplanner.general.common.SecurityTestHelper;
import eu.jrichter.cashplanner.general.common.base.AbstractRestServiceTest;
import io.oasp.module.basic.common.api.config.SpringProfileConstants;

/**
 * This configuration class provides {@code @Bean} annotated methods. It is applied to a test class by using the
 * following class annotation: {@code @SpringApplicationConfiguration(classes = TestConfig.class)}. Hence, beans
 * provided by {@code @Bean} annotated methods will not be available outside the test configuration. <br/>
 * <br/>
 * See {@link AbstractRestServiceTest} as an example.
 *
 */
@Configuration
@Profile(SpringProfileConstants.JUNIT)
public class TestConfig {

  /**
   * The constructor.
   */
  public TestConfig() {

    super();
  }

  /**
   * @return an instance of type {@code RestTestClientBuilder}
   */
  @Bean
  public RestTestClientBuilder restTestClientBuilder() {

    return new RestTestClientBuilder();
  }

  /**
   * @return an instance of type {@link SecurityTestHelper}
   */
  @Bean
  public SecurityTestHelper securityTestHelper() {

    return new SecurityTestHelper();
  }

  /**
   * @param flyway an instance of type {@link Flyway}.
   * @return an instance of type {@link DbTestHelper}.
   */
  @Bean
  public DbTestHelper dbTestHelper(Flyway flyway) {

    return new DbTestHelper(flyway);

  }

}
