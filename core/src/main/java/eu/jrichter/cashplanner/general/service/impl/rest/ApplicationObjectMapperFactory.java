package eu.jrichter.cashplanner.general.service.impl.rest;

import javax.inject.Named;

import com.fasterxml.jackson.databind.jsontype.NamedType;

import io.oasp.module.rest.service.impl.json.ObjectMapperFactory;

/**
 * The MappingFactory class to resolve polymorphic conflicts within the cashplanner application.
 *
 */
@Named("ApplicationObjectMapperFactory")
public class ApplicationObjectMapperFactory extends ObjectMapperFactory {

  /**
   * The constructor.
   */
  public ApplicationObjectMapperFactory() {

    super();
    // register polymorphic base classes

    @SuppressWarnings("unused") // ... as long as there are no sub-classes registered
    NamedType[] subtypes;
    // register mapping for polymorphic sub-classes

  }
}
