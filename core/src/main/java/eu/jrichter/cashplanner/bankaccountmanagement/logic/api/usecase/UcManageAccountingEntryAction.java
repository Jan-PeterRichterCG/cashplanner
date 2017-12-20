package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase;

/**
 * This enumeration defines actions taken if an AccountingEntry to be imported already has a matching entity in the
 * database.
 *
 * @author jrichter
 * @since 0.0.1
 */
public enum UcManageAccountingEntryAction {

  /**
   * Update only the ValueDate and update the entity only if these dates are not equal.
   */
  UPDATE_VALUE_DATE,

  /**
   * do nothing.
   */
  NONE;

}
