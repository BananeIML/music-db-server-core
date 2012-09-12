package de.bananeiml.musicdb.dao.core;

import de.bananeiml.musicdb.dao.service.CommonService;
import de.bananeiml.musicdb.dao.service.GeneralService;
import javax.persistence.EntityManager;

/**
 *
 * @author Martin Scholl
 */
public interface DAOManager {
    EntityManager getEntityManager();
    CommonService getCommonService();
    GeneralService getGeneralService();
    StaticEntityCache getCache();
}
