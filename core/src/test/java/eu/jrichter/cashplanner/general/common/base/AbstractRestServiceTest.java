package eu.jrichter.cashplanner.general.common.base;

import javax.inject.Inject;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import eu.jrichter.cashplanner.SpringBootApp;
import eu.jrichter.cashplanner.general.common.DbTestHelper;
import eu.jrichter.cashplanner.general.common.RestTestClientBuilder;
import eu.jrichter.cashplanner.general.common.SecurityTestHelper;
import eu.jrichter.cashplanner.general.service.impl.config.TestConfig;
import io.oasp.module.test.common.base.SubsystemTest;

/**
 * Abstract base class for {@link SubsystemTest}s which runs the tests within a local server. <br/>
 * <br/>
 * The local server's port is randomly assigned.
 *
 */

@SpringBootTest(classes = { TestConfig.class, SpringBootApp.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractRestServiceTest extends SubsystemTest {

  /**
   * The port of the web server during the test.
   */
  @LocalServerPort
  protected int port;

  /**
   * The {@code SecurityTestHelper}.
   */
  @Inject
  private SecurityTestHelper securityTestHelper;

  @Inject
  private DbTestHelper dbTestHelper;

  /**
   * The {@code RestTestClientBuilder}.
   */
  @Inject
  private RestTestClientBuilder restTestClientBuilder;

  /**
   * The {@code JacksonJsonProvider}
   */
  @Inject
  private JacksonJsonProvider jacksonJsonProvider;

  /**
   * Sets up the test.
   */
  @Override
  protected void doSetUp() {

    super.doSetUp();
    this.restTestClientBuilder.setLocalServerPort(this.port);
    this.restTestClientBuilder.setJacksonJsonProvider(this.jacksonJsonProvider);

  }

  /**
   * Cleans up the test.
   */
  @Override
  protected void doTearDown() {

    super.doTearDown();
  }

  /**
   * @return the {@link SecurityTestHelper}
   */
  public SecurityTestHelper getSecurityTestHelper() {

    return this.securityTestHelper;
  }

  /**
   * @return the {@link DbTestHelper}
   */
  public DbTestHelper getDbTestHelper() {

    return this.dbTestHelper;
  }

  /**
   * @return the {@link RestTestClientBuilder}
   */
  public RestTestClientBuilder getRestTestClientBuilder() {

    return this.restTestClientBuilder;
  }

}
