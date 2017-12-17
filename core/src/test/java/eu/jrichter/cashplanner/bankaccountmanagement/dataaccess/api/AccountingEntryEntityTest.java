package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api;

import java.math.BigDecimal;

import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.junit.Test;

import io.oasp.module.test.common.base.BaseTest;

/**
 * TODO jrichter This type ...
 *
 * @author jrichter
 * @since 0.0.1
 */
public class AccountingEntryEntityTest extends BaseTest {

  private static final String USD = "USD";

  private static final BigDecimal FIFTYCENT = BigDecimal.valueOf(50, 2);

  /**
   * Test whether writing the moneyAmount will keep the amount and currency in a consistent state
   */
  @Test
  public void testSetMoneyAmountReadAmountAndCurrency() {

    AccountingEntryEntity entity = new AccountingEntryEntity();

    entity.setMoneyAmount(Money.of(FIFTYCENT, Monetary.getCurrency(USD)));

    assertThat(entity.getCurrency()).isEqualTo(USD);
    assertThat(entity.getAmount()).isEqualByComparingTo(FIFTYCENT);
  }

  /**
   * Test whether writing the amount and currency will keep the MoneyAmount in a consistent state
   */
  @Test
  public void testSetAmountAndCurrencyReadMoneyAmount() {

    AccountingEntryEntity entity = new AccountingEntryEntity();

    entity.setAmount(FIFTYCENT);
    entity.setCurrency(USD);

    Money delta = entity.getMoneyAmount().subtract(Money.of(FIFTYCENT, Monetary.getCurrency(USD)));

    assertThat(delta.isZero()).isTrue();
    assertThat(delta.getCurrency().toString()).isEqualTo(USD);
  }
}
