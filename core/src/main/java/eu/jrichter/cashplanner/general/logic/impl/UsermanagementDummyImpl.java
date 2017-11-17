package eu.jrichter.cashplanner.general.logic.impl;

import eu.jrichter.cashplanner.general.common.api.UserProfile;
import eu.jrichter.cashplanner.general.common.api.Usermanagement;
import eu.jrichter.cashplanner.general.common.api.datatype.Role;
import eu.jrichter.cashplanner.general.common.api.to.UserDetailsClientTo;
import eu.jrichter.cashplanner.general.common.base.AbstractBeanMapperSupport;

import javax.inject.Named;

import org.springframework.stereotype.Component;

/**
 * Implementation of {@link Usermanagement}.
 */
@Named
@Component
public class UsermanagementDummyImpl extends AbstractBeanMapperSupport implements Usermanagement {

  @Override
  public UserProfile findUserProfileByLogin(String login) {
    // this is only a dummy - please replace with a real implementation
    UserDetailsClientTo profile = new UserDetailsClientTo();
    profile.setName(login);
    profile.setFirstName("Peter");
    profile.setLastName(login);
    profile.setRole(Role.CHIEF);
    return profile;
  }

}
