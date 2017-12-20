package eu.jrichter.cashplanner.bankaccountmanagement.logic.base.usecase;

import javax.inject.Inject;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.dao.AccountingEntryDao;
import eu.jrichter.cashplanner.general.logic.base.AbstractUc;

/**
 * Abstract use case for AccountingEntrys, which provides access to the commonly necessary data access objects.
 */
public class AbstractAccountingEntryUc extends AbstractUc {

  /** @see #getAccountingEntryDao() */
  @Inject
  private AccountingEntryDao accountingEntryDao;

  /**
   * Returns the field 'accountingEntryDao'.
   * 
   * @return the {@link AccountingEntryDao} instance.
   */
  public AccountingEntryDao getAccountingEntryDao() {

    return this.accountingEntryDao;
  }

}
