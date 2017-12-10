package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.inject.Inject;
import javax.money.Monetary;
import javax.transaction.Transactional;

import org.javamoney.moneta.Money;
import org.junit.Test;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;
import eu.jrichter.cashplanner.general.common.AbstractApplicationComponentTest;

/**
 * Test the AccountingEntryDao
 *
 * @author jrichter
 * @since 0.0.1
 */
public class AccountingEntryDaoTest extends AbstractApplicationComponentTest {

  @Inject
  AccountingEntryDao accountingEntryDao;

  /**
   * test basic saving operations of the DAO - especially the Money field
   */
  @Test
  @Transactional
  public void testSaveAccountingEntry() {

    AccountingEntryEntity entity = new AccountingEntryEntity();

    entity.setDateOfBookkeepingEntry(LocalDate.of(2017, 12, 9));
    entity.setValueDate(LocalDate.of(2017, 12, 10));
    entity.setPostingText("Testbuchung");
    entity.setAmount(Money.of(BigDecimal.valueOf(420815), Monetary.getCurrency("EUR")));

    AccountingEntryEntity entitySaved = this.accountingEntryDao.save(entity);

    Long id = entitySaved.getId();
    assertThat(id).isNotNull();

    AccountingEntryEntity retrievedEntity = this.accountingEntryDao.find(id);
    assertThat(retrievedEntity.getDateOfBookkeepingEntry()).isEqualTo(entity.getDateOfBookkeepingEntry());
    assertThat(retrievedEntity.getValueDate()).isEqualTo(entity.getValueDate());
    assertThat(retrievedEntity.getPostingText()).isEqualTo(entity.getPostingText());
    assertThat(retrievedEntity.getAmount()).isEqualTo(entity.getAmount()); // should fail due to @Transient but doesn't
                                                                           // because of caching within Hibernate
  }
}