package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.Table;

import eu.jrichter.cashplanner.bankaccountmanagement.common.api.AccountingEntry;
import eu.jrichter.cashplanner.general.dataaccess.api.ApplicationPersistenceEntity;

/**
 * The implementation of the {@link AccountingEntry}
 *
 * @author jrichter
 * @since 0.0.1
 */
@Entity
@Table(name = "AccountingEntry")
public class AccountingEntryEntity extends ApplicationPersistenceEntity implements AccountingEntry {

  private static final long serialVersionUID = 1L;

  private LocalDate dateOfBookkeepingEntry;

  private LocalDate valueDate;

  private String postingText;

  private BigDecimal amount;

  private Currency currency;

  @Override
  public LocalDate getDateOfBookkeepingEntry() {

    return this.dateOfBookkeepingEntry;
  }

  @Override
  public void setDateOfBookkeepingEntry(LocalDate dateOfBookkeepingEntry) {

    this.dateOfBookkeepingEntry = dateOfBookkeepingEntry;
  }

  @Override
  public LocalDate getValueDate() {

    return this.valueDate;
  }

  @Override
  public void setValueDate(LocalDate valueDate) {

    this.valueDate = valueDate;
  }

  @Override
  public String getPostingText() {

    return this.postingText;
  }

  @Override
  public void setPostingText(String postingText) {

    this.postingText = postingText;
  }

  @Override
  public BigDecimal getAmount() {

    return this.amount;
  }

  @Override
  public void setAmount(BigDecimal amount) {

    this.amount = amount;
  }

  @Override
  public Currency getCurrency() {

    return this.currency;
  }

  @Override
  public void setCurrency(Currency currency) {

    this.currency = currency;
  }

  @Override
  public String toString() {

    StringBuffer result = new StringBuffer();

    result.append("[");
    result.append("ID: ");
    result.append(getId());
    result.append(", Date of Bookkeeping: ");
    result.append(this.dateOfBookkeepingEntry);
    result.append(", ValueDate: ");
    result.append(this.valueDate);
    result.append(", PostingText: ");
    result.append(this.postingText);
    result.append(", Amount/Currency: ");
    result.append(this.amount);
    result.append(" ");
    result.append(this.currency);
    result.append("]");

    return result.toString();
  }

}
