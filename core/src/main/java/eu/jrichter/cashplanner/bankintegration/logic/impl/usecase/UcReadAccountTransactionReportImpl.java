package eu.jrichter.cashplanner.bankintegration.logic.impl.usecase;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.inject.Named;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.jrichter.cashplanner.bankintegration.common.api.AccountTransactionReport.AccountTransaction;
import eu.jrichter.cashplanner.bankintegration.common.api.exception.ReadFileException;
import eu.jrichter.cashplanner.bankintegration.common.api.exception.SyntaxErrorException;
import eu.jrichter.cashplanner.bankintegration.logic.api.to.AccountTransactionReportTo;
import eu.jrichter.cashplanner.bankintegration.logic.api.to.AccountTransactionTo;
import eu.jrichter.cashplanner.bankintegration.logic.api.usecase.UcReadAccountTransactionReport;

/**
 * Use case implementation for reading account transaction reports
 *
 * @author jrichter
 * @since 0.0.1
 */
@Named
public class UcReadAccountTransactionReportImpl implements UcReadAccountTransactionReport {

  private static final Logger LOG = LoggerFactory.getLogger(UcReadAccountTransactionReportImpl.class);

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  @Override
  public AccountTransactionReportTo readAccountTransactionReportFile(String filename) {

    String data = "";
    try {
      data = new Tika().parseToString(new File(filename));
    } catch (Exception e) {
      LOG.error("Reading the file resulted in an Exception: " + e.getMessage());
      throw new ReadFileException(e, filename);
    }

    LOG.debug("File read: " + data);

    return parseAccountTransactionReporString(data);
  }

  /**
   * Quick & dirty parser for Sparda Bank Hamburg eG Account Transaction Reports
   *
   * @param in the Account Transaction Reports as a String
   * @return the AccountTransactionReportTo filled with the data from the Report String
   */
  private AccountTransactionReportTo parseAccountTransactionReporString(String in) {

    AccountTransactionReportTo atr = new AccountTransactionReportTo();

    Scanner scanner = new Scanner(in);
    scanner.useDelimiter(Pattern.compile(";|\\R")); // ; or any line break ( we don't care for line breaks)
    while (scanner.hasNext()) {
      String token = scanner.next();

      LOG.debug("Token read (" + token.length() + " characters): " + token);

      if (token.length() == 0) // skip empty lines
        continue;

      String strippedToken = strip(token);

      if (strippedToken.equals("Kontoinhaber:")) {
        atr.setAccountHolderName(strip(scanner.next()));
        LOG.debug("AccountHolderName set to \"" + atr.getAccountHolderName() + "\"");
        continue;
      }

      if (strippedToken.equals("Kundennummer:")) {
        atr.setCustomerId(strip(scanner.next()));
        LOG.debug("CustomerId set to \"" + atr.getCustomerId() + "\"");
        continue;
      }

      if (strippedToken.equals("Umsätze ab")) {
        parseStartDateEndDateAccountNumberBalanceAmountCurrency(atr, token, scanner);
        LOG.debug("StarDate set to \"" + atr.getStartDate() + "\"");
        LOG.debug("EndDate set to \"" + atr.getEndDate() + "\"");
        LOG.debug("AccountNumber set to \"" + atr.getAccountNumber() + "\"");
        LOG.debug("BalanceAmount set to \"" + atr.getBalanceAmount() + "\"");
        LOG.debug("BalanceCurrency set to \"" + atr.getBalanceCurrency() + "\"");
        continue;
      }

      if (strippedToken.equals("Weitere gewählte Suchoptionen:")) {
        atr.setAdditionalSearchOptions(strip(scanner.next()));
        LOG.debug("AdditionalSearchOptions set to \"" + atr.getAdditionalSearchOptions() + "\"");
        continue;
      }

      if (strippedToken.equals("Buchungstag")) {
        parseTransactions(atr, token, scanner);
        List<AccountTransaction> atl = atr.getAccountTransactions();
        if (atl != null) {
          int index = 0;
          for (AccountTransaction at : atl) {
            LOG.debug("AccountTransaction " + index++ + " set to " + at.getDateOfBookkeepingEntry() + ","
                + at.getValueDate() + ", \"" + at.getPostingText() + "\", " + at.getAmount() + ", " + at.getCurrency()
                + ", \"" + at.getMarker() + "\"");
          }
        }
        continue;
      }

      atr.setTitle(strippedToken);
      LOG.debug("Title set to \"" + atr.getTitle() + "\"");
      continue;
    }
    scanner.close();

    return atr;
  }

  private String strip(String token) {

    return token.substring(1, token.length() - 1); // strip first and last char - always '"'
  }

  private void parseStartDateEndDateAccountNumberBalanceAmountCurrency(AccountTransactionReportTo atr,
      String startingToken, Scanner scanner) {

    if (!strip(startingToken).equals("Umsätze ab"))
      throw new IllegalArgumentException("wrong starting token: " + startingToken);

    String token = scanner.next();
    if (!strip(token).equals("Enddatum"))
      throw new SyntaxErrorException(token);

    token = scanner.next();
    if (!strip(token).equals("Kontonummer"))
      throw new SyntaxErrorException(token);

    token = scanner.next();
    if (!strip(token).equals("Saldo"))
      throw new SyntaxErrorException(token);

    token = scanner.next();
    if (!strip(token).equals("Währung"))
      throw new SyntaxErrorException(token);

    token = strip(scanner.next());
    atr.setStartDate(LocalDate.parse(token, formatter));

    token = strip(scanner.next());
    atr.setEndDate(LocalDate.parse(token, formatter));

    token = strip(scanner.next());
    atr.setAccountNumber(token);

    token = strip(scanner.next());
    String normalizedToken = token.replaceAll("\\.", "").replace(",", "."); // we know that we have German locale
    LOG.debug("normalizedToken for balanceAmount " + normalizedToken);
    atr.setBalanceAmount(new BigDecimal(normalizedToken));

    token = strip(scanner.next());
    atr.setBalanceCurrency(Currency.getInstance(token));
  }

  private void parseTransactions(AccountTransactionReportTo atr, String startingToken, Scanner scanner) {

    if (!strip(startingToken).equals("Buchungstag"))
      throw new IllegalArgumentException("wrong starting token: " + startingToken);

    String token = scanner.next();
    if (!strip(token).equals("Wertstellungstag"))
      throw new SyntaxErrorException(token);

    token = scanner.next();
    if (!strip(token).equals("Verwendungszweck"))
      throw new SyntaxErrorException(token);

    token = scanner.next();
    if (!strip(token).equals("Umsatz"))
      throw new SyntaxErrorException(token);

    token = scanner.next();
    if (!strip(token).equals("Währung"))
      throw new SyntaxErrorException(token);

    atr.setAccountTransactions(new ArrayList<AccountTransaction>());

    while (scanner.hasNext()) {
      token = scanner.next();

      if (strip(token).equals("* noch nicht ausgeführte Umsätze"))
        return;

      AccountTransaction at = new AccountTransactionTo();

      at.setDateOfBookkeepingEntry(LocalDate.parse(strip(token), formatter));

      token = strip(scanner.next());
      at.setValueDate(LocalDate.parse(token, formatter));

      token = strip(scanner.next());
      at.setPostingText(token);

      token = strip(scanner.next());
      String normalizedToken = token.replaceAll("\\.", "").replace(",", "."); // we know that we have German locale
      LOG.debug("normalizedToken for amount " + normalizedToken);
      at.setAmount(new BigDecimal(normalizedToken));

      token = strip(scanner.next());
      at.setCurrency(Currency.getInstance(token));

      token = strip(scanner.next());
      at.setMarker(token);

      atr.getAccountTransactions().add(at);
    }
  }
}
