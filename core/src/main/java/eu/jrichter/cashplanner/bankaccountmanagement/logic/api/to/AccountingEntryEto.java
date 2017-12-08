package eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to;

import java.time.LocalDate;

import org.javamoney.moneta.Money;

import eu.jrichter.cashplanner.bankaccountmanagement.common.api.AccountingEntry;
import eu.jrichter.cashplanner.general.common.api.to.AbstractEto;

/**
 * Entity transport object of AccountingEntry
 */
public class AccountingEntryEto extends AbstractEto implements AccountingEntry {

  private static final long serialVersionUID = 1L;

  private LocalDate dateOfBookkeepingEntry;

  private LocalDate valueDate;

  private String postingText;

  private Money amount;

  @Override
  public LocalDate getDateOfBookkeepingEntry() {

    return dateOfBookkeepingEntry;
  }

  @Override
  public void setDateOfBookkeepingEntry(LocalDate dateOfBookkeepingEntry) {

    this.dateOfBookkeepingEntry = dateOfBookkeepingEntry;
  }

  @Override
  public LocalDate getValueDate() {

    return valueDate;
  }

  @Override
  public void setValueDate(LocalDate valueDate) {

    this.valueDate = valueDate;
  }

  @Override
  public String getPostingText() {

    return postingText;
  }

  @Override
  public void setPostingText(String postingText) {

    this.postingText = postingText;
  }

  @Override
  public Money getAmount() {

    return amount;
  }

  @Override
  public void setAmount(Money amount) {

    this.amount = amount;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.dateOfBookkeepingEntry == null) ? 0 : this.dateOfBookkeepingEntry.hashCode());
    result = prime * result + ((this.valueDate == null) ? 0 : this.valueDate.hashCode());
    result = prime * result + ((this.postingText == null) ? 0 : this.postingText.hashCode());
    result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    // class check will be done by super type EntityTo!
    if (!super.equals(obj)) {
      return false;
    }
    AccountingEntryEto other = (AccountingEntryEto) obj;
    if (this.dateOfBookkeepingEntry == null) {
      if (other.dateOfBookkeepingEntry != null) {
        return false;
      }
    } else if (!this.dateOfBookkeepingEntry.equals(other.dateOfBookkeepingEntry)) {
      return false;
    }
    if (this.valueDate == null) {
      if (other.valueDate != null) {
        return false;
      }
    } else if (!this.valueDate.equals(other.valueDate)) {
      return false;
    }
    if (this.postingText == null) {
      if (other.postingText != null) {
        return false;
      }
    } else if (!this.postingText.equals(other.postingText)) {
      return false;
    }
    if (this.amount == null) {
      if (other.amount != null) {
        return false;
      }
    } else if (!this.amount.equals(other.amount)) {
      return false;
    }
    return true;
  }
}
