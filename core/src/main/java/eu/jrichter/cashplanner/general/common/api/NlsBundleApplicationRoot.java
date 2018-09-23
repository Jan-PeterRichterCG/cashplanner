package eu.jrichter.cashplanner.general.common.api;

import javax.inject.Named;

import net.sf.mmm.util.nls.api.NlsBundle;
import net.sf.mmm.util.nls.api.NlsBundleMessage;
import net.sf.mmm.util.nls.api.NlsMessage;

/**
 * This is the {@link NlsBundle} for this application.
 *
 */
public interface NlsBundleApplicationRoot extends NlsBundle {

  /**
   * @see eu.jrichter.cashplanner.general.common.api.exception.IllegalEntityStateException
   *
   * @param entity is the entity relevant for the error.
   * @param state is the state of the entity that caused the operation to fail.
   * @return the {@link NlsMessage}.
   */
  @NlsBundleMessage("The entity {entity} is in state {state}!")
  NlsMessage errorIllegalEntityState(@Named("entity") Object entity, @Named("state") Object state);

  /**
   * @see eu.jrichter.cashplanner.general.common.api.exception.IllegalEntityStateException
   *
   * @param entity is the entity relevant for the error.
   * @param currentState is the current state of the entity.
   * @param newState is the new state for the entity that is illegal.
   * @return the {@link NlsMessage}.
   */
  @NlsBundleMessage("The entity {entity} in state {currentState} can not be changed to state {newState}!")
  NlsMessage errorIllegalEntityStateChange(@Named("entity") Object entity, @Named("currentState") Object currentState,
      @Named("newState") Object newState);

  /**
   * @see eu.jrichter.cashplanner.general.common.api.exception.IllegalEntityStateException
   *
   * @param object is the entity relevant for the error.
   * @param property is the property of the entity that can not be changed.
   * @return the {@link NlsMessage}.
   */
  @NlsBundleMessage("The property {property} of object {object} can not be changed!")
  NlsMessage errorIllegalPropertyChange(@Named("object") Object object, @Named("property") Object property);

  /**
   * @see eu.jrichter.cashplanner.general.common.api.exception.NoActiveUserException
   *
   * @return the {@link NlsMessage}.
   */
  @NlsBundleMessage("There is currently no user logged in")
  NlsMessage errorNoActiveUser();

  /**
   * @see eu.jrichter.cashplanner.bankintegration.common.api.exception.ReadFileException
   *
   * @param filename is the filename used to read when the error occurred.
   * @return the {@link NlsMessage}.
   */
  @NlsBundleMessage("The file {path} could not be read!")
  NlsMessage errorReadFile(@Named("path") Object path);

  /**
   * @see eu.jrichter.cashplanner.bankintegration.common.api.exception.SyntaxErrorException
   *
   * @param syntaxError is the syntax error that occurred while parsing the input.
   * @return the {@link NlsMessage}.
   */
  @NlsBundleMessage("The input contains a syntax error at or near \"{syntaxError}\".")
  NlsMessage errorSyntaxError(@Named("syntaxError") Object syntaxError);

}
