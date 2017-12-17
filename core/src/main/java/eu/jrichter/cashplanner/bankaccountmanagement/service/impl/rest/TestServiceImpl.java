package eu.jrichter.cashplanner.bankaccountmanagement.service.impl.rest;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.inject.Inject;
import javax.inject.Named;
import javax.money.Monetary;
import javax.transaction.Transactional;

import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;
import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.dao.AccountingEntryDao;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntryEto;
import eu.jrichter.cashplanner.bankaccountmanagement.service.api.rest.TestService;
import eu.jrichter.cashplanner.general.logic.api.UseCase;
import eu.jrichter.cashplanner.general.logic.base.AbstractUc;

/**
 * This is just a quick & dirty hack in order to be able to trigger some system behavior.
 *
 * @author jrichter
 * @since 0.0.1
 */
@Named("TestRestService")
@UseCase
public class TestServiceImpl extends AbstractUc implements TestService {

  @Inject
  private AccountingEntryDao accountingEntryDao;

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(TestServiceImpl.class);

  @Override
  @Transactional
  public AccountingEntryEto getAccountingEntry(long id) {

    LOG.info("TestService called with id " + id);

    AccountingEntryEntity entity = new AccountingEntryEntity();
    entity.setDateOfBookkeepingEntry(LocalDate.now());
    entity.setValueDate(LocalDate.of(2017, 12, 15));
    entity.setPostingText("Test entry");
    entity.setMoneyAmount(Money.of(BigDecimal.valueOf(-47110815, 2), Monetary.getCurrency("EUR")));

    entity = this.accountingEntryDao.save(entity);

    LOG.info("Entity saved: " + entity.toString());

    return getBeanMapper().map(entity, AccountingEntryEto.class);
  }

}
