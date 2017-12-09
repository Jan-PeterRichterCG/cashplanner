package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api;

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

  private Money amount;

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
  @Transient // as long as we have no mapping for Money
  public Money getAmount() {

    return this.amount;
  }

  @Override
  public void setAmount(Money amount) {

    this.amount = amount;
  }

}
