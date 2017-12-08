package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to;

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

  private Money amount;

  /**
   * The constructor.
   */
  public AccountingEntrySearchCriteriaTo() {

    super();
  }

  public LocalDate getDateOfBookkeepingEntry() {

    return dateOfBookkeepingEntry;
  }

  public void setDateOfBookkeepingEntry(LocalDate dateOfBookkeepingEntry) {

    this.dateOfBookkeepingEntry = dateOfBookkeepingEntry;
  }

  public LocalDate getValueDate() {

    return valueDate;
  }

  public void setValueDate(LocalDate valueDate) {

    this.valueDate = valueDate;
  }

  public String getPostingText() {

    return postingText;
  }

  public void setPostingText(String postingText) {

    this.postingText = postingText;
  }

  public Money getAmount() {

    return amount;
  }

  public void setAmount(Money amount) {

    this.amount = amount;
  }

}
