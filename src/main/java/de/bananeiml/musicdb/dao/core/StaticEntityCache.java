package de.bananeiml.musicdb.dao.core;

import de.bananeiml.musicdb.dao.entity.Key;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Martin Scholl
 */
public class StaticEntityCache {
    
    
    private final transient DAOManager daoManager;
    private final transient Map<String, SoftReference<Key>> keyCache;
    
    StaticEntityCache(final DAOManager daoManager){
        if(daoManager == null){
            throw new IllegalArgumentException("daoManager must not be null"); // NOI18N
        }
        
        this.daoManager = daoManager;
        
        keyCache = new HashMap<String, SoftReference<Key>>(24);
    }
    
    public Key getKey(final String key){
        if(keyCache.isEmpty()){
            initKeyCache();
        }
        
        final SoftReference<Key> keySoftref = keyCache.get(key);
        
        assert keySoftref != null : "illegal initialisation state";
        
        Key keyEntity = keySoftref.get();
        if(keyEntity == null){
            // entity has been collected, reinit
            keyEntity = daoManager.getGeneralService().getKey(key);
            keyCache.put(key, new SoftReference<Key>(keyEntity));
        }
        
        return keyEntity;
    }
    
    private void initKeyCache(){
        final List<Key> keys = daoManager.getCommonService().getAllEntities(Key.class);
        
        for(final Key key : keys){
            keyCache.put(key.getKey(), new SoftReference<Key>(key));
        }
    }
}
