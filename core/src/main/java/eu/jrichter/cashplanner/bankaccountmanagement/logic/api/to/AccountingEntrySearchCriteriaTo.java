package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.javamoney.moneta.Money;

import io.oasp.module.jpa.common.api.to.SearchCriteriaTo;

/**
 * This is the {@link SearchCriteriaTo search criteria} {@link net.sf.mmm.util.transferobject.api.TransferObject TO}
 * used to find {@link eu.jrichter.cashplanner.bankaccountmanagement.common.api.AccountingEntry}s.
 *
 */
public class AccountingEntrySearchCriteriaTo extends SearchCriteriaTo {

  private static final long serialVersionUID = 1L;

  private LocalDate dateOfBookkeepingEntry;

  private LocalDate valueDate;

  private String postingText;

  private BigDecimal amount;

  private String currency;

  private Money moneyAmount;

  /**
   * The constructor.
   */
  public AccountingEntrySearchCriteriaTo() {

    super();
  }

  public LocalDate getDateOfBookkeepingEntry() {

    return this.dateOfBookkeepingEntry;
  }

  public void setDateOfBookkeepingEntry(LocalDate dateOfBookkeepingEntry) {

    this.dateOfBookkeepingEntry = dateOfBookkeepingEntry;
  }

  public LocalDate getValueDate() {

    return this.valueDate;
  }

  public void setValueDate(LocalDate valueDate) {

    this.valueDate = valueDate;
  }

  public String getPostingText() {

    return this.postingText;
  }

  public void setPostingText(String postingText) {

    this.postingText = postingText;
  }

  public BigDecimal getAmount() {

    return this.amount;
  }

  public void setAmount(BigDecimal amount) {

    this.amount = amount;
  }

  public String getCurrency() {

    return this.currency;
  }

  public void setCurrency(String currency) {

    this.currency = currency;
  }

  // searching for MoneyAmount will probably not work since this field is @Transient
  // public Money getMoneyAmount() {
  //
  // return moneyAmount;
  // }
  //
  // public void setMoneyAmount(Money moneyAmount) {
  //
  // this.moneyAmount = moneyAmount;
  // }

}
