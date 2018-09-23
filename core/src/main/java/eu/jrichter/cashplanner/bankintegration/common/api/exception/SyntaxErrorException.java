package eu.jrichter.cashplanner.bankintegration.common.api.exception;

import eu.jrichter.cashplanner.general.common.api.NlsBundleApplicationRoot;
import eu.jrichter.cashplanner.general.common.api.exception.ApplicationTechnicalException;

/**
 * This exception is thrown if a file contains a syntax error
 *
 * @author jrichter
 * @since 0.0.1
 */
public class SyntaxErrorException extends ApplicationTechnicalException {

  /**
   * default serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   *
   * @param syntaxError the syntax error
   */
  public SyntaxErrorException(String syntaxError) {

    super(createBundle(NlsBundleApplicationRoot.class).errorSyntaxError(syntaxError));
  }

  /**
   * The other constructor.
   *
   * @param error the causing exception - e.g. from a date parser
   *
   * @param syntaxError the syntax error
   */
  public SyntaxErrorException(Throwable error, String syntaxError) {

    super(error, createBundle(NlsBundleApplicationRoot.class).errorSyntaxError(syntaxError));
  }
}
