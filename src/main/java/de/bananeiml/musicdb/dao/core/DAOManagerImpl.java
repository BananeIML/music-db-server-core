package de.bananeiml.musicdb.dao.core;

import de.bananeiml.musicdb.dao.service.CommonService;
import de.bananeiml.musicdb.dao.service.GeneralService;
import de.bananeiml.musicdb.dao.service.impl.CommonServiceImpl;
import de.bananeiml.musicdb.dao.service.impl.GeneralServiceImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Martin Scholl
 */
public final class DAOManagerImpl implements DAOManager {
    
    private final transient EntityManagerFactory emf;
    private final transient CommonService commonService;
    private final transient GeneralService generalService;
    private final transient StaticEntityCache cache;
    
    public  DAOManagerImpl(){
        emf = Persistence.createEntityManagerFactory("mdbDaoPU"); // NOI18N
        commonService = new CommonServiceImpl(getEntityManager());
        generalService = new GeneralServiceImpl(getEntityManager());
        cache = new StaticEntityCache(this);
    }
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public CommonService getCommonService(){
        return commonService;
    }

    @Override
    public GeneralService getGeneralService() {
        return generalService;
    }
    
    @Override
    public StaticEntityCache getCache() {
        return cache;
    }
}
