package eu.jrichter.cashplanner.bankintegration.common.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This interface represents a report of account transactions as can be imported by some interface, e.g. reading a file
 * downloaded from a Internet banking site.
 *
 * As of now, only the specific content of the report issued by Sparda Bank Hamburg eG is supported.
 *
 * @author jrichter
 * @since 0.0.1
 */
public interface AccountTransactionReport {

  /**
   * @return the title of the account transaction report
   */
  public String getTitle();

  /**
   * @param title new value of {@link #getTitle()}
   */
  public void setTitle(String title);

  /**
   * @return the name of the account holder
   */
  public String getAccountHolderName();

  /**
   * @param accountHolderName new value of {@link #getAccountHolderName()}
   */
  public void setAccountHolderName(String accountHolderName);

  /**
   * @return the Id of the customer
   */
  public String getCustomerId();

  /**
   * @param customerId new value of {@link #getCustomerId()}
   */
  public void setCustomerId(String customerId);

  /**
   * @return the start date of the report
   */
  public LocalDate getStartDate();

  /**
   * @param startDate new value of {@link #getStartDate()}
   */
  public void setStartDate(LocalDate startDate);

  /**
   * @return the end date of the report
   */
  public LocalDate getEndDate();

  /**
   * @param endDate new value of {@link #getEndDate()}
   */
  public void setEndDate(LocalDate endDate);

  /**
   * @return the account number
   */
  public String getAccountNumber();

  /**
   * @param accountNumber new value of {@link #getAccountNumber()}
   */
  public void setAccountNumber(String accountNumber);

  /**
   * @return the balance amount
   */
  public BigDecimal getBalanceAmount();

  /**
   * @param balanceAmount new value of {@link #getBalanceAmount()}
   */
  public void setBalanceAmount(BigDecimal balanceAmount);

  /**
   * @return the additional search options
   */
  public String getAdditionalSearchOptions();

  /**
   * @param additionalSearchOptions new value of {@link #getAdditionalSearchOptions()}
   */
  public void setAdditionalSearchOptions(String additionalSearchOptions);

  /**
   * @return the balance currency
   */
  public Currency getBalanceCurrency();

  /**
   * @param balanceCurrency new value of {@link #getBalanceCurrency()}
   */
  public void setBalanceCurrency(Currency balanceCurrency);

  /**
   * @return the list of account transactions
   */
  public List<AccountTransaction> getAccountTransactions();

  /**
   * @param accountTransactions new value of {@link #getAccountTransactions()}
   */
  public void setAccountTransactions(List<AccountTransaction> accountTransactions);

  /**
   * This interface represents a single transaction (line) of the report
   *
   * @author jrichter
   * @since 0.0.1
   */
  public interface AccountTransaction {

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

    /**
     * @return the marker - a "*" marks transactions that have not been processed yet
     */
    @NotNull
    public String getMarker();

    /**
     * @param marker new value of {@link #getMarker}
     */
    public void setMarker(String marker);

  }

}
