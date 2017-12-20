package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase;

import java.util.List;

import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntryEto;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntrySearchCriteriaTo;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

public interface UcFindAccountingEntry {

  /**
   * Returns a AccountingEntry by its id 'id'.
   *
   * @param id The id 'id' of the AccountingEntry.
   * @return The {@link AccountingEntryEto} with id 'id'
   */
  AccountingEntryEto findAccountingEntry(Long id);

  /**
   * Returns a paginated list of AccountingEntrys matching the search criteria.
   *
   * @param criteria the {@link AccountingEntrySearchCriteriaTo}.
   * @return the {@link List} of matching {@link AccountingEntryEto}s.
   */
  PaginatedListTo<AccountingEntryEto> findAccountingEntryEtos(AccountingEntrySearchCriteriaTo criteria);

}
