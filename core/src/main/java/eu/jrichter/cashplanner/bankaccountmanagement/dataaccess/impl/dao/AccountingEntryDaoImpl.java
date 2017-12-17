package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.impl.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.inject.Named;

import com.mysema.query.alias.Alias;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;
import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.dao.AccountingEntryDao;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntrySearchCriteriaTo;
import eu.jrichter.cashplanner.general.dataaccess.base.dao.ApplicationDaoImpl;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * This is the implementation of {@link AccountingEntryDao}.
 */
@Named
public class AccountingEntryDaoImpl extends ApplicationDaoImpl<AccountingEntryEntity> implements AccountingEntryDao {

  /**
   * The constructor.
   */
  public AccountingEntryDaoImpl() {

    super();
  }

  @Override
  public Class<AccountingEntryEntity> getEntityClass() {

    return AccountingEntryEntity.class;
  }

  @Override
  public PaginatedListTo<AccountingEntryEntity> findAccountingEntrys(AccountingEntrySearchCriteriaTo criteria) {

    AccountingEntryEntity accountingentry = Alias.alias(AccountingEntryEntity.class);
    EntityPathBase<AccountingEntryEntity> alias = Alias.$(accountingentry);
    JPAQuery query = new JPAQuery(getEntityManager()).from(alias);

    LocalDate dateOfBookkeepingEntry = criteria.getDateOfBookkeepingEntry();
    if (dateOfBookkeepingEntry != null) {
      query.where(Alias.$(accountingentry.getDateOfBookkeepingEntry()).eq(dateOfBookkeepingEntry));
    }
    LocalDate valueDate = criteria.getValueDate();
    if (valueDate != null) {
      query.where(Alias.$(accountingentry.getValueDate()).eq(valueDate));
    }
    String postingText = criteria.getPostingText();
    if (postingText != null) {
      query.where(Alias.$(accountingentry.getPostingText()).eq(postingText));
    }
    BigDecimal amount = criteria.getAmount();
    if (amount != null) {
      query.where(Alias.$(accountingentry.getAmount()).eq(amount));
    }
    String currency = criteria.getCurrency();
    if (currency != null) {
      query.where(Alias.$(accountingentry.getCurrency()).eq(currency));
    }
    // searching for MoneyAmount will not work since it is @Transient
    // Money moneyAmount = criteria.getMoneyAmount();
    // if (moneyAmount != null) {
    // query.where(Alias.$(accountingentry.getMoneyAmount()).eq(moneyAmount));
    // }
    return findPaginated(criteria, query, alias);
  }

}