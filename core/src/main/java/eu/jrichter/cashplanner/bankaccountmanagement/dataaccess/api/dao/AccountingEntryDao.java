package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.dao;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntrySearchCriteriaTo;
import eu.jrichter.cashplanner.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Data access interface for AccountingEntry entities
 */
public interface AccountingEntryDao extends ApplicationDao<AccountingEntryEntity> {

  /**
   * Finds the {@link AccountingEntryEntity accountingentrys} matching the given
   * {@link AccountingEntrySearchCriteriaTo}.
   *
   * @param criteria is the {@link AccountingEntrySearchCriteriaTo}.
   * @return the {@link PaginatedListTo} with the matching {@link AccountingEntryEntity} objects.
   */
  PaginatedListTo<AccountingEntryEntity> findAccountingEntrys(AccountingEntrySearchCriteriaTo criteria);
}
