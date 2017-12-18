package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.jrichter.cashplanner.bankaccountmanagement.common.api.AccountingEntryComparator;
import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntrySearchCriteriaTo;
import eu.jrichter.cashplanner.general.common.AbstractApplicationComponentTest;

/**
 * Test the AccountingEntryDao
 *
 * @author jrichter
 * @since 0.0.1
 */
public class AccountingEntryDaoTest extends AbstractApplicationComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(AccountingEntryDaoTest.class);

  @Inject
  AccountingEntryDao accountingEntryDao;

  /**
   * test basic saving operations of the DAO
   */
  @Test
  @Transactional
  public void testSaveAccountingEntry() {

    AccountingEntryEntity entity = new AccountingEntryEntity();

    entity.setDateOfBookkeepingEntry(LocalDate.of(2017, 12, 9));
    entity.setValueDate(LocalDate.of(2017, 12, 10));
    entity.setPostingText("Testbuchung");
    entity.setAmount(BigDecimal.valueOf(-420815, 2));
    entity.setCurrency(Currency.getInstance("EUR"));

    AccountingEntryEntity entitySaved = this.accountingEntryDao.save(entity);

    Long id = entitySaved.getId();
    assertThat(id).isNotNull();

    AccountingEntryEntity retrievedEntity = this.accountingEntryDao.find(id);
    assertThat(retrievedEntity.getDateOfBookkeepingEntry()).isEqualTo(entity.getDateOfBookkeepingEntry());
    assertThat(retrievedEntity.getValueDate()).isEqualTo(entity.getValueDate());
    assertThat(retrievedEntity.getPostingText()).isEqualTo(entity.getPostingText());
    assertThat(retrievedEntity.getAmount()).isEqualTo(entity.getAmount());
    assertThat(retrievedEntity.getCurrency()).isEqualTo(entity.getCurrency());

    LOG.info("Entity saved: " + entitySaved.toString());
  }

  /**
   * test basic finding operations of the DAO - based on the "master data" in the database
   */
  @Test
  @Transactional
  public void testFindUniqueAccountingEntryByCriteria() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    criteria.setAmount(BigDecimal.valueOf(4299, 2));
    criteria.setCurrency(Currency.getInstance("EUR"));
    criteria.setDateOfBookkeepingEntry(LocalDate.of(2017, 12, 02));
    criteria.setPostingText("Test MasterData 1");
    criteria.setValueDate(LocalDate.of(2017, 12, 01));

    List<AccountingEntryEntity> results = this.accountingEntryDao.findAccountingEntrys(criteria).getResult();

    assertThat(results.size()).isEqualTo(1);

    AccountingEntryEntity entity = results.get(0);
    assertThat(entity.getId()).isEqualTo(10000);

    LOG.info("Entity found: " + entity.toString());
  }

  /**
   * test basic finding operations of the DAO - based on the "master data" in the database
   */
  @Test
  @Transactional
  public void testFindMutipleAccountingEntriesByCriteria() {

    AccountingEntrySearchCriteriaTo criteria = new AccountingEntrySearchCriteriaTo();
    criteria.setCurrency(Currency.getInstance("EUR"));

    List<AccountingEntryEntity> results = this.accountingEntryDao.findAccountingEntrys(criteria).getResult();

    Comparator<? super AccountingEntryEntity> c = new AccountingEntryComparator();
    results.sort(c);

    assertThat(results.size()).isEqualTo(2);

    AccountingEntryEntity entity = results.get(0);
    assertThat(entity.getId()).isEqualTo(10002);
    LOG.info("Entity 0 found: " + entity.toString());

    entity = results.get(1);
    assertThat(entity.getId()).isEqualTo(10000);
    LOG.info("Entity 1 found: " + entity.toString());
  }

}
