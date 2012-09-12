package de.bananeiml.musicdb.dao.service.impl;

import de.bananeiml.musicdb.dao.entity.CommonEntity;
import de.bananeiml.musicdb.dao.entity.NamedEntity;
import de.bananeiml.musicdb.dao.service.CommonService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Martin Scholl
 */
public class CommonServiceImpl implements CommonService{
    
    private final transient EntityManager em;

    public CommonServiceImpl(final EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public <T extends CommonEntity> T store(final T entity) {
        final EntityManager e = getEntityManager();
        e.getTransaction().begin();
        T ret = null;
        if (e.contains(entity)) {
            e.persist(entity);
        } else {
            ret = e.merge(entity);
        }
        e.getTransaction().commit();

        return (ret == null) ? entity : ret;
    }

    @Override
    public void delete(final CommonEntity ce) {
        final EntityManager e = getEntityManager();
        e.getTransaction().begin();
        final Object toDelete;
        if (e.contains(ce)) {
            toDelete = ce;
        } else {
            toDelete = e.merge(ce);
        }
        e.remove(toDelete);
        e.getTransaction().commit();
    }

    @Override
    public void delete(final List<CommonEntity> entities) {
        for (final CommonEntity ce : entities) {
            delete(ce);
        }
    }

    @Override
    public <T extends CommonEntity> T getEntity(final Class<T> entity, long id) throws NoResultException {
        final EntityManager entityManager = getEntityManager();
        final T e = entityManager.find(entity, id);
        // throw exception to be consistent with em.getSingleResult
        if (e == null) {
            throw new NoResultException("'" + entity.getSimpleName() + "' with id '" + id + "' does not exist"); // NOI18N
        }

        return e;
    }

    @Override
    public <T extends CommonEntity> List<T> getAllEntities(final Class<T> entity) {
        final EntityManager entityManager = getEntityManager();
        final Query q = entityManager.createQuery("FROM " + entity.getSimpleName()); // NOI18N

        return q.getResultList();
    }

    @Override
    public <T extends NamedEntity> T getEntity(final Class<T> entity, final String name) throws NoResultException {
        final EntityManager entityManager = getEntityManager();
        final Query q = entityManager.createQuery("FROM " + entity.getSimpleName() + " WHERE name = :name"); // NOI18N
        q.setParameter("name", name);                                                                        // NOI18N

        return (T)q.getSingleResult();
    }

    @Override
    public <T extends NamedEntity> boolean contains(final Class<T> entity, final String name) {
        try {
            return getEntity(entity, name) != null;
        } catch (final NoResultException ex) {
            return false;
        }
    }
}
