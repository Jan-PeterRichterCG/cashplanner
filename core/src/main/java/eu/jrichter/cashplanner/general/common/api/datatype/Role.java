package eu.jrichter.cashplanner.general.common.api.datatype;

import java.security.Principal;

/**
 * jrichter This enum type works as a registry for all roles, i.e. collections of permissions, in the application.
 *
 * @author jrichter
 * @since 0.0.1
 */
public enum Role implements Principal {

  @SuppressWarnings("javadoc")
  DUMMY_ROLE("DummyRole");

  private final String name;

  private Role(String name) {

    this.name = name;
  }

  @Override
  public String getName() {

    return this.name;
  }
}
