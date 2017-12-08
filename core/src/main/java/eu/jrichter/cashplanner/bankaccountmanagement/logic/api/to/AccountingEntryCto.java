package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to;

import eu.jrichter.cashplanner.general.common.api.to.AbstractCto;

/**
 * Composite transport object of AccountingEntry
 */
public class AccountingEntryCto extends AbstractCto {

  private static final long serialVersionUID = 1L;

  private AccountingEntryEto accountingEntry;

  public AccountingEntryEto getAccountingEntry() {

    return accountingEntry;
  }

  public void setAccountingEntry(AccountingEntryEto accountingEntry) {

    this.accountingEntry = accountingEntry;
  }

}
