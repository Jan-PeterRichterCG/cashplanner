package eu.jrichter.cashplanner.bankaccountmanagement.common.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eu.jrichter.cashplanner.general.common.api.ApplicationEntity;

/**
 * This interface represents an entry in a banking account, i.e. an atomic and (from a business perspective) immutable
 * financial transaction. An accounting entry can also be seen as a booking that adds or subtracts a monetary amount
 * to/from an account.
 *
 * @author jrichter
 * @since 0.0.1
 */
public interface AccountingEntry extends ApplicationEntity {

  /**
   * @return the date of bookkeeping entry, i.e. the date when the accounting entry was entered into the books.
   */
  @NotNull
  public LocalDate getDateOfBookkeepingEntry();

  /**
   * @param dateOfBookkeepingEntry new value of {@link #getDateOfBookkeepingEntry}.
   */
  public void setDateOfBookkeepingEntry(LocalDate dateOfBookkeepingEntry);

  /**
   * @return the value date, i.e. the date when the account entry becomes / became effective
   */
  @NotNull
  public LocalDate getValueDate();

  /**
   * @param valueDate new value of {@link #getValueDate}.
   */
  public void setValueDate(LocalDate valueDate);

  /**
   * @return the posting text
   */
  @NotNull
  @Size(max = 255)
  public String getPostingText();

  /**
   * @param postingText new value of {@link #getPostingText}.
   */
  public void setPostingText(String postingText);

  /**
   * @return the amount of the account entry as a Big Decimal
   */
  @NotNull
  public BigDecimal getAmount();

  /**
   * @param amount new value of {@link #getAmount}.
   */
  public void setAmount(BigDecimal amount);

  /**
   * @return the currency
   */
  @NotNull
  public Currency getCurrency();

  /**
   * @param currency new value of {@link #getCurrency}
   */
  public void setCurrency(Currency currency);

}
