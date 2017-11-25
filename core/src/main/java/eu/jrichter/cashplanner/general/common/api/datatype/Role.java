package eu.jrichter.cashplanner.general.common.api.datatype;

import java.security.Principal;

public enum Role implements Principal {

  DUMMY_ROLE("Dummy Role");

  private final String name;

  private Role(String name) {

    this.name = name;
  }

  @Override
  public String getName() {

    return this.name;
  }
}
