package eu.jrichter.cashplanner.bankaccountmanagement.common.api;

import java.util.Comparator;

import eu.jrichter.cashplanner.bankaccountmanagement.dataaccess.api.AccountingEntryEntity;

/**
 * A simple Comparator for AccountingEntries. As of now the comparator can only compare two AccountingEntryEntities
 * based on the ValueDate and in ascending order which will be the most prominent ordering relation.
 *
 * When this comparator will be enhanced by the ability to set the ordering criteria later, this should remain the
 * default and should be made available through the default constructor.
 *
 * @author jrichter
 * @since 0.0.1
 */
public class AccountingEntryComparator implements Comparator<AccountingEntryEntity> {

  @Override
  public int compare(AccountingEntryEntity o1, AccountingEntryEntity o2) {

    return o1.getValueDate().compareTo(o2.getValueDate());
  }

}
