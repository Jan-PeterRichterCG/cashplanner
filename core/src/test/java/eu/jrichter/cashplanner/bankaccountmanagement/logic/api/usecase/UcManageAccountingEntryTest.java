package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.javatuples.Pair;
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

    AccountingEntryEto eto = new AccountingEntryEto();
    eto.setId(4711l);

    this.thrown.expect(IllegalArgumentException.class);
    this.thrown.expectMessage(Matchers.containsString("4711"));
    this.bankacountmanagement.importAccountingEntry(eto, UcManageAccountingEntryAction.NONE);
    // never reached
  }

  private Pair<AccountingEntryEto, Integer> getExistingEntryWithIdSetToNullAndTotalNumberOfEntries() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    AccountingEntryEto entry = etos.get(0);
    entry.setId(null);

    return Pair.with(entry, etos.size());
  }

  private AccountingEntryEto importAccountingEntryWithActionAndAssert(AccountingEntryEto ae,
      UcManageAccountingEntryAction action, boolean shouldReturnNull, Integer newTotalNumberOfEntries) {

    AccountingEntryEto importedAccountingEntry = this.bankacountmanagement.importAccountingEntry(ae, action);
    if (shouldReturnNull)
      assertThat(importedAccountingEntry).isNull(); // indicating that no new entry was written
    else
      assertThat(importedAccountingEntry).isNotNull(); // indicating that a new/updated entry was written

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isEqualTo(newTotalNumberOfEntries);

    return importedAccountingEntry;
  }

  /**
   * Tests that an entry that is already in the database is not imported a second time.
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesDoesNotImportExistingEntries() {

    Pair<AccountingEntryEto, Integer> pair = getExistingEntryWithIdSetToNullAndTotalNumberOfEntries();

    importAccountingEntryWithActionAndAssert(pair.getValue0(), UcManageAccountingEntryAction.UPDATE_VALUE_DATE, true,
        pair.getValue1());
  }

  /**
   * Tests that an entry that is already in the database is updated if the value date is changed
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesUpdatesOnValueDate() {

    Pair<AccountingEntryEto, Integer> pair = getExistingEntryWithIdSetToNullAndTotalNumberOfEntries();

    AccountingEntryEto etoToUpdate = pair.getValue0();
    etoToUpdate.setValueDate(etoToUpdate.getValueDate().plusDays(1));

    AccountingEntryEto importedAccountingEntry = importAccountingEntryWithActionAndAssert(etoToUpdate,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE, false, pair.getValue1());

    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(importedAccountingEntry.getId());
    assertThat(etoFound.getValueDate().isEqual(etoToUpdate.getValueDate()));
  }

  /**
   * Tests that an entry that is already in the database is not updated if the value date is changed and the action is
   * NONE
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesDoesNotUpdateOnValueDateIfActionIsNone() {

    Pair<AccountingEntryEto, Integer> pair = getExistingEntryWithIdSetToNullAndTotalNumberOfEntries();

    AccountingEntryEto etoToUpdate = pair.getValue0();
    etoToUpdate.setValueDate(etoToUpdate.getValueDate().plusDays(1));

    importAccountingEntryWithActionAndAssert(etoToUpdate, UcManageAccountingEntryAction.NONE, true, pair.getValue1());
  }

  /**
   * Tests that an entry that is entered in the database if the dateOfBookkeepingEntry is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnDateOfBookkeepingEntry() {

    Pair<AccountingEntryEto, Integer> pair = getExistingEntryWithIdSetToNullAndTotalNumberOfEntries();

    AccountingEntryEto etoToUpdate = pair.getValue0();
    etoToUpdate.setDateOfBookkeepingEntry(etoToUpdate.getDateOfBookkeepingEntry().plusDays(1));

    AccountingEntryEto importedAccountingEntry = importAccountingEntryWithActionAndAssert(etoToUpdate,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE, false, pair.getValue1() + 1);

    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(importedAccountingEntry.getId());
    assertThat(etoFound.getDateOfBookkeepingEntry().isEqual(etoToUpdate.getDateOfBookkeepingEntry()));
  }

  /**
   * Tests that an entry that is entered in the database if the postingText is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnPostingText() {

    Pair<AccountingEntryEto, Integer> pair = getExistingEntryWithIdSetToNullAndTotalNumberOfEntries();

    AccountingEntryEto etoToUpdate = pair.getValue0();
    etoToUpdate.setPostingText(etoToUpdate.getPostingText().concat("foo"));

    AccountingEntryEto importedAccountingEntry = importAccountingEntryWithActionAndAssert(etoToUpdate,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE, false, pair.getValue1() + 1);

    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(importedAccountingEntry.getId());
    assertThat(etoFound.getPostingText()).isEqualTo(etoToUpdate.getPostingText());
  }

  /**
   * Tests that an entry that is entered in the database if the currency is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnCurrency() {

    Pair<AccountingEntryEto, Integer> pair = getExistingEntryWithIdSetToNullAndTotalNumberOfEntries();

    AccountingEntryEto etoToUpdate = pair.getValue0();
    if (etoToUpdate.getCurrency().equals(Currency.getInstance("EUR")))
      etoToUpdate.setCurrency(Currency.getInstance("USD"));
    else
      etoToUpdate.setCurrency(Currency.getInstance("EUR"));

    AccountingEntryEto importedAccountingEntry = importAccountingEntryWithActionAndAssert(etoToUpdate,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE, false, pair.getValue1() + 1);

    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(importedAccountingEntry.getId());
    assertThat(etoFound.getCurrency()).isEqualTo(etoToUpdate.getCurrency());
  }

  /**
   * Tests that an entry that is entered in the database if the amount is different
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesImportsOnAmount() {

    Pair<AccountingEntryEto, Integer> pair = getExistingEntryWithIdSetToNullAndTotalNumberOfEntries();

    AccountingEntryEto etoToUpdate = pair.getValue0();
    etoToUpdate.setAmount(etoToUpdate.getAmount().add(BigDecimal.valueOf(1, 2)));

    AccountingEntryEto importedAccountingEntry = importAccountingEntryWithActionAndAssert(etoToUpdate,
        UcManageAccountingEntryAction.UPDATE_VALUE_DATE, false, pair.getValue1() + 1);

    AccountingEntryEto etoFound = this.bankacountmanagement.findAccountingEntry(importedAccountingEntry.getId());
    assertThat(etoFound.getAmount()).isEqualTo(etoToUpdate.getAmount());
  }

  /**
   * Tests that transactions from an account transaction report file are correctly imported
   */
  @Test
  @Transactional
  public void testImportAccountingEntriesFromFile() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    // just use empty search criteria to get all entities
    List<AccountingEntryEto> etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();

    assertThat(etos.size()).isGreaterThan(0); // make sure that there is some test data in the database

    String path = "C:/OASP/IDE/workspaces/main/cashplanner/core/src/test/resources/file/umsaetze-47110815-2017-12-21-21-30-49.csv";
    int imported = this.bankacountmanagement.importAccountingEntriesFromFile(path);

    assertThat(imported).isEqualTo(9); // there are 9 transactions but one is marked "*"

    List<AccountingEntryEto> etos2 = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos2.size()).isEqualTo(etos.size() + 9); // there should be 8 new entries in the database

    criteria = new AccountingEntrySearchCriteriaTo();
    criteria.setAmount(BigDecimal.valueOf(-4548, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText().indexOf("Sirius Cybernetics Corporation SEPA-BASISLASTSCHRIFT")).isZero();

    criteria.setAmount(BigDecimal.valueOf(987654, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText().indexOf("CAPGEMINI DEUTSCHLAND GMBHSEPA-LOHN/GEHALT SVWZ+ SALA")).isZero();

    criteria.setAmount(BigDecimal.valueOf(-40000, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText().indexOf("BARCLAYCARD SEPA-BASISLASTSCHRIFT SVWZ+ CDBL Kreditkar")).isZero();

    criteria.setAmount(BigDecimal.valueOf(89164, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText()
        .indexOf("CAPGEMINI DEUTSCHLAND GMBHSEPA-ÜBERWEISUNG SVWZ+ 2017 123456 EREF+ 5140767")).isZero();

    criteria.setAmount(BigDecimal.valueOf(10000, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText().indexOf("Arthur Dent SEPA-ÜBERWEISUNG SVWZ+ Weihnachtsgesc henk")).isZero();

    criteria.setAmount(BigDecimal.valueOf(-10000, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText().indexOf("12345678 GAA  11.07 KARTE 123456789   16.12 CSC3 1070")).isZero();

    criteria.setAmount(BigDecimal.valueOf(-1099, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText().indexOf("NETFLIX INTERNATIONAL B.V.SEPA-BASISLASTSCHRIFT SVWZ+")).isZero();

    criteria.setAmount(BigDecimal.valueOf(9915, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText()
        .indexOf("CAPGEMINI DEUTSCHLAND GMBHSEPA-ÜBERWEISUNG SVWZ+ 2017 654321 EREF+ 5138250")).isZero();

    criteria.setAmount(BigDecimal.valueOf(-2203, 2));
    etos = this.bankacountmanagement.findAccountingEntryEtos(criteria).getResult();
    assertThat(etos.size()).isEqualTo(1);
    assertThat(etos.get(0).getPostingText().indexOf("Turn- und SportvereinigungMagrathea von 1892 e.V.")).isZero();
  }
}
