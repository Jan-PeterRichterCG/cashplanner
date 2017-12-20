package eu.jrichter.cashplanner.bankaccountmanagement.logic.impl.usecase;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntryEto;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntrySearchCriteriaTo;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.usecase.UcFindAccountingEntry;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.base.usecase.AbstractAccountingEntryUc;
import eu.jrichter.cashplanner.general.logic.api.UseCase;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Use case implementation for searching, filtering and getting AccountingEntrys
 */
@Named
@UseCase
@Validated
@Transactional
public class UcFindAccountingEntryImpl extends AbstractAccountingEntryUc implements UcFindAccountingEntry {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcFindAccountingEntryImpl.class);

  @Override
  public AccountingEntryEto findAccountingEntry(Long id) {

    LOG.debug("Get AccountingEntry with id {} from database.", id);
    return getBeanMapper().map(getAccountingEntryDao().findOne(id), AccountingEntryEto.class);
  }

  @Override
  public PaginatedListTo<AccountingEntryEto> findAccountingEntryEtos(AccountingEntrySearchCriteriaTo criteria) {

    criteria.limitMaximumPageSize(MAXIMUM_HIT_LIMIT);
    PaginatedListTo<AccountingEntryEntity> accountingentrys = getAccountingEntryDao().findAccountingEntrys(criteria);
    return mapPaginatedEntityList(accountingentrys, AccountingEntryEto.class);
  }

}
