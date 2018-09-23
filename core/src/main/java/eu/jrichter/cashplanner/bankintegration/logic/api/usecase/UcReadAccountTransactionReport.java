package eu.jrichter.cashplanner.bankintegration.logic.api.usecase;

import eu.jrichter.cashplanner.bankintegration.logic.api.to.AccountTransactionReportTo;

/**
 * Adapter use case for reading account transaction reports
 *
 * @author jrichter
 * @since 0.0.1
 */
public interface UcReadAccountTransactionReport {

  /**
   * Read an account transaction report from a .csv file. For the time being, the file must provided locally. TODO
   * refactor for web GUI upload
   *
   * @param path the path of the report file
   * @return the structured account transaction report
   */
  public AccountTransactionReportTo readAccountTransactionReportFile(String path);

  /**
   * the maximum length of a account transaction report file - avoids out of memory
   */
  public final int MAX_ACCOUNT_TRANSACTION_REPORT_FILE_LENGTH = 1000000000;

}
