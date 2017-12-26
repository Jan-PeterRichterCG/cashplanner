package eu.jrichter.cashplanner.bankintegration.logic.impl;

import javax.inject.Inject;
import javax.inject.Named;

import eu.jrichter.cashplanner.bankintegration.logic.api.Bankintegration;
import eu.jrichter.cashplanner.bankintegration.logic.api.to.AccountTransactionReportTo;
import eu.jrichter.cashplanner.bankintegration.logic.api.usecase.UcReadAccountTransactionReport;
import eu.jrichter.cashplanner.general.logic.base.AbstractComponentFacade;

/**
 * Implementation of component interface of bankintegration
 *
 * @author jrichter
 * @since 0.0.1
 */
@Named
public class BankintegrationImpl extends AbstractComponentFacade implements Bankintegration {

  @Inject
  private UcReadAccountTransactionReport ucReadAccountTransactionReport;

  /**
   * The constructor.
   */
  public BankintegrationImpl() {

    super();
  }

  @Override
  public AccountTransactionReportTo readAccountTransactionReportFile(String filename) {

    return this.ucReadAccountTransactionReport.readAccountTransactionReportFile(filename);
  }

}
