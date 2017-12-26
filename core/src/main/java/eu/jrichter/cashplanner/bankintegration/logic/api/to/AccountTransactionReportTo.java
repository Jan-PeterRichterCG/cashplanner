package eu.jrichter.cashplanner.bankintegration.logic.api.to;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import net.sf.mmm.util.transferobject.api.DataTo;

import eu.jrichter.cashplanner.bankintegration.common.api.AccountTransactionReport;

/**
 * The TO implementation of {@link AccountTransactionReport}
 *
 * @author jrichter
 * @since 0.0.1
 */
public class AccountTransactionReportTo extends DataTo implements AccountTransactionReport {

  private static final long serialVersionUID = 1L;

  private String title;

  private String accountHolderName;

  private String customerId;

  private LocalDate startDate;

  private LocalDate endDate;

  private String accountNumber;

  private BigDecimal balanceAmount;

  private Currency balanceCurrency;

  private String additionalSearchOptions;

  private List<AccountTransaction> accountTransactions;

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void setTitle(String title) {

    this.title = title;
  }

  @Override
  public String getAccountHolderName() {

    return this.accountHolderName;
  }

  @Override
  public void setAccountHolderName(String accountHolderName) {

    this.accountHolderName = accountHolderName;
  }

  @Override
  public String getCustomerId() {

    return this.customerId;
  }

  @Override
  public void setCustomerId(String customerId) {

    this.customerId = customerId;
  }

  @Override
  public LocalDate getStartDate() {

    return this.startDate;
  }

  @Override
  public void setStartDate(LocalDate startDate) {

    this.startDate = startDate;
  }

  @Override
  public LocalDate getEndDate() {

    return this.endDate;
  }

  @Override
  public void setEndDate(LocalDate endDate) {

    this.endDate = endDate;
  }

  @Override
  public String getAccountNumber() {

    return this.accountNumber;
  }

  @Override
  public void setAccountNumber(String accountNumber) {

    this.accountNumber = accountNumber;
  }

  @Override
  public BigDecimal getBalanceAmount() {

    return this.balanceAmount;
  }

  @Override
  public void setBalanceAmount(BigDecimal balanceAmount) {

    this.balanceAmount = balanceAmount;
  }

  @Override
  public Currency getBalanceCurrency() {

    return this.balanceCurrency;
  }

  @Override
  public void setBalanceCurrency(Currency balanceCurrency) {

    this.balanceCurrency = balanceCurrency;
  }

  @Override
  public String getAdditionalSearchOptions() {

    return this.additionalSearchOptions;
  }

  @Override
  public void setAdditionalSearchOptions(String additionalSearchOptions) {

    this.additionalSearchOptions = additionalSearchOptions;
  }

  @Override
  public List<AccountTransaction> getAccountTransactions() {

    return this.accountTransactions;
  }

  @Override
  public void setAccountTransactions(List<AccountTransaction> accountTransactions) {

    this.accountTransactions = accountTransactions;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
    result = prime * result + ((this.accountHolderName == null) ? 0 : this.accountHolderName.hashCode());
    result = prime * result + ((this.customerId == null) ? 0 : this.customerId.hashCode());
    result = prime * result + ((this.startDate == null) ? 0 : this.startDate.hashCode());
    result = prime * result + ((this.endDate == null) ? 0 : this.endDate.hashCode());
    result = prime * result + ((this.accountNumber == null) ? 0 : this.accountNumber.hashCode());
    result = prime * result + ((this.balanceAmount == null) ? 0 : this.balanceAmount.hashCode());
    result = prime * result + ((this.balanceCurrency == null) ? 0 : this.balanceCurrency.hashCode());
    result = prime * result + ((this.additionalSearchOptions == null) ? 0 : this.additionalSearchOptions.hashCode());
    result = prime * result + ((this.accountTransactions == null) ? 0 : this.accountTransactions.hashCode());
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
    AccountTransactionReportTo other = (AccountTransactionReportTo) obj;
    if (this.title == null) {
      if (other.title != null) {
        return false;
      }
    } else if (!this.title.equals(other.title)) {
      return false;
    }
    if (this.accountHolderName == null) {
      if (other.accountHolderName != null) {
        return false;
      }
    } else if (!this.accountHolderName.equals(other.accountHolderName)) {
      return false;
    }
    if (this.customerId == null) {
      if (other.customerId != null) {
        return false;
      }
    } else if (!this.customerId.equals(other.customerId)) {
      return false;
    }
    if (this.startDate == null) {
      if (other.startDate != null) {
        return false;
      }
    } else if (!this.startDate.equals(other.startDate)) {
      return false;
    }
    if (this.endDate == null) {
      if (other.endDate != null) {
        return false;
      }
    } else if (!this.endDate.equals(other.endDate)) {
      return false;
    }
    if (this.accountNumber == null) {
      if (other.accountNumber != null) {
        return false;
      }
    } else if (!this.accountNumber.equals(other.accountNumber)) {
      return false;
    }
    if (this.balanceAmount == null) {
      if (other.balanceAmount != null) {
        return false;
      }
    } else if (!this.balanceAmount.equals(other.balanceAmount)) {
      return false;
    }
    if (this.balanceCurrency == null) {
      if (other.balanceCurrency != null) {
        return false;
      }
    } else if (!this.balanceCurrency.equals(other.balanceCurrency)) {
      return false;
    }
    if (this.additionalSearchOptions == null) {
      if (other.additionalSearchOptions != null) {
        return false;
      }
    } else if (!this.additionalSearchOptions.equals(other.additionalSearchOptions)) {
      return false;
    }
    if (this.accountTransactions == null) {
      if (other.accountTransactions != null) {
        return false;
      }
    } else if (!this.accountTransactions.equals(other.accountTransactions)) {
      return false;
    }
    return true;
  }

}
