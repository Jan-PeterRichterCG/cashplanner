package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to;

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
public class AccountingEntryEtoTest extends BaseTest {

  private static final String USD = "USD";

  private static final BigDecimal FIFTYCENT = BigDecimal.valueOf(50, 2);

  /**
   * Test whether writing the moneyAmount will keep the amount and currency in a consistent state
   */
  @Test
  public void testSetMoneyAmountReadAmountAndCurrency() {

    AccountingEntryEto eto = new AccountingEntryEto();

    eto.setMoneyAmount(Money.of(FIFTYCENT, Monetary.getCurrency(USD)));

    assertThat(eto.getCurrency()).isEqualTo(USD);
    assertThat(eto.getAmount()).isEqualByComparingTo(FIFTYCENT);
  }

  /**
   * Test whether writing the amount and currency will keep the MoneyAmount in a consistent state
   */
  @Test
  public void testSetAmountAndCurrencyReadMoneyAmount() {

    AccountingEntryEto eto = new AccountingEntryEto();

    eto.setAmount(FIFTYCENT);
    eto.setCurrency(USD);

    Money delta = eto.getMoneyAmount().subtract(Money.of(FIFTYCENT, Monetary.getCurrency(USD)));

    assertThat(delta.isZero()).isTrue();
    assertThat(delta.getCurrency().toString()).isEqualTo(USD);
  }

}
