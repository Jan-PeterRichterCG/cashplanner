package eu.jrichter.cashplanner.bankaccountmanagement.logic.impl;

import javax.inject.Inject;
import javax.inject.Named;

import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.Bankaccountmanagement;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntryEto;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntrySearchCriteriaTo;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase.UcFindAccountingEntry;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase.UcManageAccountingEntry;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase.UcManageAccountingEntryAction;
import eu.jrichter.cashplanner.general.logic.base.AbstractComponentFacade;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Implementation of component interface of bankaccountmanagement
 */
@Named
public class BankaccountmanagementImpl extends AbstractComponentFacade implements Bankaccountmanagement {

  @Inject
  private UcFindAccountingEntry ucFindAccountingEntry;

  @Inject
  private UcManageAccountingEntry ucManageAccountingEntry;

  /**
   * The constructor.
   */
  public BankaccountmanagementImpl() {

    super();
  }

  @Override
  public AccountingEntryEto findAccountingEntry(Long id) {

    return this.ucFindAccountingEntry.findAccountingEntry(id);
  }

  @Override
  public PaginatedListTo<AccountingEntryEto> findAccountingEntryEtos(AccountingEntrySearchCriteriaTo criteria) {

    return this.ucFindAccountingEntry.findAccountingEntryEtos(criteria);
  }

  @Override
  public AccountingEntryEto saveAccountingEntry(AccountingEntryEto accountingentry) {

    return this.ucManageAccountingEntry.saveAccountingEntry(accountingentry);
  }

  @Override
  public boolean deleteAccountingEntry(Long id) {

    return this.ucManageAccountingEntry.deleteAccountingEntry(id);
  }

  @Override
  public AccountingEntryEto importAccountingEntry(AccountingEntryEto accountingEnty,
      UcManageAccountingEntryAction action) {

    return this.ucManageAccountingEntry.importAccountingEntry(accountingEnty, action);
  }

  @Override
  public int importAccountingEntriesFromFile(String path) {

    return this.ucManageAccountingEntry.importAccountingEntriesFromFile(path);
  }
}
