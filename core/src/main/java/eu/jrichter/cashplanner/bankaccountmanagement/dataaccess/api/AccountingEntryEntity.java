package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.javamoney.moneta.Money;

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

  private String currency;

  private Money moneyAmount;

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
    // if currency has already been set for this entity, copy amount and currency to the transient moneyAmount;
    if (null != this.currency) {
      this.moneyAmount = Money.of(amount, this.currency);
    }
  }

  @Override
  public String getCurrency() {

    return this.currency;
  }

  @Override
  public void setCurrency(String currency) {

    this.currency = currency;
    // if amount has already been set for this entity, copy amount and currency to the transient moneyAmount;
    if (null != this.amount) {
      this.moneyAmount = Money.of(this.amount, currency);
    }

  }

  @Override
  @Transient // we persist the amount and currency but leave the (redundant) moneyAmount transient
  public Money getMoneyAmount() {

    return this.moneyAmount;
  }

  @Override
  public void setMoneyAmount(Money moneyAmount) {

    this.moneyAmount = moneyAmount;
    this.amount = moneyAmount.getNumberStripped();
    this.currency = moneyAmount.getCurrency().getCurrencyCode();
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
    result.append(", MoneyAmount: ");
    result.append(this.moneyAmount);
    result.append("]");

    return result.toString();
  }

}
