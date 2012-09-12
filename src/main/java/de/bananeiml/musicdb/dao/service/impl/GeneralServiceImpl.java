package de.bananeiml.musicdb.dao.service.impl;

import de.bananeiml.musicdb.dao.entity.Album;
import de.bananeiml.musicdb.dao.entity.Artist;
import de.bananeiml.musicdb.dao.entity.Key;
import de.bananeiml.musicdb.dao.entity.Key.FittingKeys;
import de.bananeiml.musicdb.dao.entity.Title;
import de.bananeiml.musicdb.dao.entity.Title.FittingTitles;
import de.bananeiml.musicdb.dao.service.GeneralService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Martin Scholl
 */
public class GeneralServiceImpl implements GeneralService {

    private final transient EntityManager em;

    public GeneralServiceImpl(final EntityManager em) {
        this.em = em;
    }
    
    public EntityManager getEntityManager(){
        return em;
    }

    @Override
    public List<Album> getAlbums(final Artist artist) {
        if(artist == null){
            throw new IllegalArgumentException("cannot get albums for null artist"); // NOI18N
        }
        
        final Query query = em.createQuery("FROM Album album WHERE album.artist = :artist"); // NOI18N
        
        return query.setParameter("artist", artist).getResultList();
    }

    @Override
    public List<Title> getTitles(final Artist artist) {
        if(artist == null){
            throw new IllegalArgumentException("cannot get titles for null artist"); // NOI18N
        }
        
        final Query query = em.createQuery("FROM Title title WHERE title.artist = :artist"); // NOI18N
        
        return query.setParameter("artist", artist).getResultList();
    }

    @Override
    public List<Title> getTitles(final Album album) {
        if(album == null){
            throw new IllegalArgumentException("cannot get titles for null artist"); // NOI18N
        }
        
        final Query query = em.createQuery("FROM Title title WHERE title.album = :album"); // NOI18N
        
        return query.setParameter("album", album).getResultList();
    }

    @Override
    public List<Title> getTitles(final Key key) {
        if(key == null){
            final Query query = em.createQuery("FROM Title title WHERE title.key IS NULL"); // NOI18N

            return query.getResultList();
        } else {
            final Query query = em.createQuery("FROM Title title WHERE title.key = :key"); // NOI18N

            return query.setParameter("key", key).getResultList();
        }
    }

    @Override
    public FittingTitles getFittingTitles(final Key key) {
        if(key == null){
            throw new IllegalArgumentException("cannot get fitting titles for null key"); // NOI18N
        }
        
        final FittingTitles fittingTitles = new FittingTitles();
        final FittingKeys fittingKeys = Key.getFittingKeys(key);
        
        fittingTitles.base = getTitles(key);
        
        final Query titlesQuery = em.createQuery("FROM Title title WHERE title.key = :key"); // NOI18N
        
        final Key lowerKey = getKey(fittingKeys.lower);
        titlesQuery.setParameter("key", lowerKey); // NOI18N
        fittingTitles.lower = titlesQuery.getResultList();
        
        final Key higherKey = getKey(fittingKeys.higher);
        titlesQuery.setParameter("key", higherKey); // NOI18N
        fittingTitles.higher = titlesQuery.getResultList();
        
        final Key pusherKey = getKey(fittingKeys.pusher);
        titlesQuery.setParameter("key", pusherKey); // NOI18N
        fittingTitles.pusher = titlesQuery.getResultList();
        
        final Key oppositeKey = getKey(fittingKeys.opposite);
        titlesQuery.setParameter("key", oppositeKey); // NOI18N
        fittingTitles.opposite = titlesQuery.getResultList();
        
        return fittingTitles;
    }

    @Override
    public Key getKey(final String key) {
        final Query keyQuery = em.createQuery("FROM Key key WHERE key.key = :keyString"); // NOI18N
        keyQuery.setParameter("keyString", key); // NOI18N
        
        return (Key)keyQuery.getSingleResult();
    }

    @Override
    public Title getTitle(String name, Artist artist) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
