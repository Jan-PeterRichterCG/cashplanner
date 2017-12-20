package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.Bankaccountmanagement;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntryEto;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntrySearchCriteriaTo;
import eu.jrichter.cashplanner.general.common.AbstractApplicationComponentTest;

/**
 * This class tests the UcManageAccountingEntry - at least the hand-written methods.
 *
 * @author jrichter
 * @since 0.0.1
 */
public class UcManageAccountingEntryTest extends AbstractApplicationComponentTest {

  @Inject
  Bankaccountmanagement bankacountmanagement;

  @SuppressWarnings("javadoc")
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Tests that in case an Eto contains a non-null Id
   *
   * @throws IllegalArgumentException IllegalArgumentException
   */
  @Test
  public void testImportAccountingEntriesEtosMustNotContainIds() throws IllegalArgumentException {

    HashSet<AccountingEntryEto> accountingEntries = new HashSet<>();
    accountingEntries.add(new AccountingEntryEto());
    accountingEntries.add(new AccountingEntryEto());
    accountingEntries.add(new AccountingEntryEto());
    accountingEntries.add(new AccountingEntryEto());
    AccountingEntryEto eto = new AccountingEntryEto();
    eto.setId(4711l);
    accountingEntries.add(eto);
    accountingEntries.add(new AccountingEntryEto());
    accountingEntries.add(new AccountingEntryEto());

    this.thrown.expect(IllegalArgumentException.class);
    this.thrown.expectMessage(Matchers.containsString("4711"));
    this.bankacountmanagement.importAccountingEntries(accountingEntries, UcManageAccountingEntryAction.NONE);
    // never reached
  }

  /**
   * Tests that an entry that is already in the database is not imported a second time.
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesDoesNotImportExistingEntries() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    for (AccountingEntryEto eto : etos) {
      eto.setId(null); // must be null to adhere to the contract
    }

    Collection<AccountingEntryEto> importedAccountingEntries;
    importedAccountingEntries = this.bankacountmanagement.importAccountingEntries(etos,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE);
    assertThat(importedAccountingEntries.size()).isEqualTo(0); // indicating that no new entry was written

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size()); // there should not be any new entry in the database
  }

  /**
   * Tests that an entry that is already in the database is updated if the value date is changed
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesUpdatesOnValueDate() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    for (AccountingEntryEto eto : etos) {
      eto.setId(null); // must be null to adhere to the contract
    }

    AccountingEntryEto etoToUpdate = etos.get(0);
    etoToUpdate.setValueDate(etoToUpdate.getValueDate().plusDays(1));

    Collection<AccountingEntryEto> importedAccountingEntries;
    importedAccountingEntries = this.bankacountmanagement.importAccountingEntries(etos,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE);
    assertThat(importedAccountingEntries.size()).isEqualTo(1); // indicating that one entry was written or updated

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size()); // there should not be any new entry in the database

    Long idInResult = ((AccountingEntryEto) (importedAccountingEntries.toArray()[0])).getId();
    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(idInResult);
    assertThat(etoFound.getValueDate().isEqual(etoToUpdate.getValueDate()));
  }

  /**
   * Tests that an entry that is already in the database is not updated if the value date is changed and the action is
   * NONE
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesDoesNotUpdateOnValueDateIfActionIsNone() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    for (AccountingEntryEto eto : etos) {
      eto.setId(null); // must be null to adhere to the contract
    }

    AccountingEntryEto etoToUpdate = etos.get(0);
    etoToUpdate.setValueDate(etoToUpdate.getValueDate().plusDays(1));

    Collection<AccountingEntryEto> importedAccountingEntries;
    importedAccountingEntries = this.bankacountmanagement.importAccountingEntries(etos,
        UcManageAccountingEntryAction.NONE);
    assertThat(importedAccountingEntries.size()).isEqualTo(0); // indicating that no entry was written or updated

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size()); // there should not be any new entry in the database
  }

  /**
   * Tests that an entry that is entered in the database if the dateOfBookkeepingEntry is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnDateOfBookkeepingEntry() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    for (AccountingEntryEto eto : etos) {
      eto.setId(null); // must be null to adhere to the contract
    }

    AccountingEntryEto etoToUpdate = etos.get(0);
    etoToUpdate.setDateOfBookkeepingEntry(etoToUpdate.getDateOfBookkeepingEntry().plusDays(1));

    Collection<AccountingEntryEto> importedAccountingEntries;
    importedAccountingEntries = this.bankacountmanagement.importAccountingEntries(etos,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE);
    assertThat(importedAccountingEntries.size()).isEqualTo(1); // indicating that one entry was written or updated

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size() + 1); // there should be one new entry in the database

    Long idInResult = ((AccountingEntryEto) (importedAccountingEntries.toArray()[0])).getId();
    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(idInResult);
    assertThat(etoFound.getDateOfBookkeepingEntry().isEqual(etoToUpdate.getDateOfBookkeepingEntry()));
  }

  /**
   * Tests that an entry that is entered in the database if the postingText is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnPostingText() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    for (AccountingEntryEto eto : etos) {
      eto.setId(null); // must be null to adhere to the contract
    }

    AccountingEntryEto etoToUpdate = etos.get(0);
    etoToUpdate.setPostingText(etoToUpdate.getPostingText().concat("foo"));

    Collection<AccountingEntryEto> importedAccountingEntries;
    importedAccountingEntries = this.bankacountmanagement.importAccountingEntries(etos,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE);
    assertThat(importedAccountingEntries.size()).isEqualTo(1); // indicating that one entry was written or updated

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size() + 1); // there should be one new entry in the database

    Long idInResult = ((AccountingEntryEto) (importedAccountingEntries.toArray()[0])).getId();
    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(idInResult);
    assertThat(etoFound.getPostingText()).isEqualTo(etoToUpdate.getPostingText());
  }

  /**
   * Tests that an entry that is entered in the database if the currency is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnCurrency() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    for (AccountingEntryEto eto : etos) {
      eto.setId(null); // must be null to adhere to the contract
    }

    AccountingEntryEto etoToUpdate = etos.get(0);
    if (etoToUpdate.getCurrency().equals(Currency.getInstance("EUR")))
      etoToUpdate.setCurrency(Currency.getInstance("USD"));
    else
      etoToUpdate.setCurrency(Currency.getInstance("EUR"));

    Collection<AccountingEntryEto> importedAccountingEntries;
    importedAccountingEntries = this.bankacountmanagement.importAccountingEntries(etos,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE);
    assertThat(importedAccountingEntries.size()).isEqualTo(1); // indicating that one entry was written or updated

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size() + 1); // there should be one new entry in the database

    Long idInResult = ((AccountingEntryEto) (importedAccountingEntries.toArray()[0])).getId();
    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(idInResult);
    assertThat(etoFound.getCurrency()).isEqualTo(etoToUpdate.getCurrency());
  }

  /**
   * Tests that an entry that is entered in the database if the amount is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnAmount() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    for (AccountingEntryEto eto : etos) {
      eto.setId(null); // must be null to adhere to the contract
    }

    AccountingEntryEto etoToUpdate = etos.get(0);
    etoToUpdate.setAmount(etoToUpdate.getAmount().add(BigDecimal.valueOf(1, 2)));

    Collection<AccountingEntryEto> importedAccountingEntries;
    importedAccountingEntries = this.bankacountmanagement.importAccountingEntries(etos,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE);
    assertThat(importedAccountingEntries.size()).isEqualTo(1); // indicating that one entry was written or updated

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size() + 1); // there should be one new entry in the database

    Long idInResult = ((AccountingEntryEto) (importedAccountingEntries.toArray()[0])).getId();
    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(idInResult);
    assertThat(etoFound.getAmount()).isEqualTo(etoToUpdate.getAmount());
  }
}
