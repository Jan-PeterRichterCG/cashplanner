package eu.jrichter.cashplanner.general.dataaccess.base.dao;

import eu.jrichter.cashplanner.general.dataaccess.api.BinaryObjectEntity;
import eu.jrichter.cashplanner.general.dataaccess.api.dao.BinaryObjectDao;

import javax.inject.Named;

/**
 * Implementation of {@link BinaryObjectDao}.
 *
 */
@Named
public class BinaryObjectDaoImpl extends ApplicationDaoImpl<BinaryObjectEntity> implements BinaryObjectDao {

  @Override
  public Class<BinaryObjectEntity> getEntityClass() {

    return BinaryObjectEntity.class;
  }

}
