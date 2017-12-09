package eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.dao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;

@Named
public class AccountingEntryDaoTestHelper {
  @Inject
  AccountingEntryDao accountingEntryDao;

  @Transactional
  Long saveAccountingEntry(AccountingEntryEntity entity) {

    AccountingEntryEntity entitySaved = this.accountingEntryDao.save(entity);

    return entitySaved.getId();
  }
}