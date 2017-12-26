package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase;

import java.util.Collection;

import eu.jrichter.cashplanner.bankaccountmanagement.common.api.AccountingEntry;
import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntryEto;

/**
 * Interface of UcManageAccountingEntry to centralize documentation and signatures of methods.
 */
public interface UcManageAccountingEntry {

  /**
   * Deletes a accountingEntry from the database by its id 'accountingEntryId'.
   *
   * @param accountingEntryId Id of the accountingEntry to delete
   * @return boolean <code>true</code> if the accountingEntry can be deleted, <code>false</code> otherwise
   */
  boolean deleteAccountingEntry(Long accountingEntryId);

  /**
   * Saves a accountingEntry and store it in the database.
   *
   * @param accountingEntry the {@link AccountingEntryEto} to create.
   * @return the new {@link AccountingEntryEto} that has been saved with ID and version.
   */
  AccountingEntryEto saveAccountingEntry(AccountingEntryEto accountingEntry);

  /**
   * Imports a {@link Collection} of {@link AccountingEntry}s in the database in an (almost) idempotent way. A new
   * {@link AccountingEntryEntity} is persisted only if there is no matching one already present in the database.
   * Therefore, the {@link AccountingEntry}s should be newly created Etos, not entities from the database. Two
   * AccountingEntries match if their postingTexts are equal AND their amount AND currency are equal AND their
   * date(s)OfBookkeepingEntry are equal.
   *
   * If the AccountingEntryEto has a matching counterpart in the database the result depends on the action parameter. If
   * action is UPDATE_VALUE_DATE only the valueDate is updated and the update takes place only if the valueDates are not
   * equal. If action is NONE no action is performed.
   *
   * @param accountingEntries the Collection of {@link AccountingEntryEto}s to be imported. The id field of each Eto
   *        must be null, anything else is considererd as an illegal argument.
   * @param action the {@link UcManageAccountingEntryAction} to be taken if a matching AccountingEntryEntity is already
   *        present in the database
   * @return the Collection of imported or updated AccountEntires with their Ids set to the Ids in the database
   */
  Collection<AccountingEntry> importAccountingEntries(Collection<? extends AccountingEntry> accountingEntries,
      UcManageAccountingEntryAction action);

  /**
   * Imports account entries from an account transaction report given as a file into the database in an (almost)
   * idempotent way using {@link #importAccountingEntries}. The UPDATE_VALUE_DATE is used to handle versions of
   * transactions across files. Also, transactions that have the marker set to anything else than null or the empty
   * string are not imported.
   *
   * Note: this method is a quick hack and will be replaced when a proper GUI with an upload functionality is built.
   *
   * @param path the path of the file to import
   * @return the number of imported or updated AccountEntires
   */
  int importAccountingEntriesFromFile(String path);

}
