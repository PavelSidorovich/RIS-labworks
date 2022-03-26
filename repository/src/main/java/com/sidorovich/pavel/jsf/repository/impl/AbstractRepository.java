package com.sidorovich.pavel.jsf.repository.impl;

import com.sidorovich.pavel.jsf.repository.CrudRepository;
import com.sidorovich.pavel.jsf.repository.Flushable;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Stateless
public abstract class AbstractRepository<T>
        implements CrudRepository<T>, Flushable {

    private static final String FIND_ALL_QUERY = "SELECT m FROM %s m";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM %s WHERE id = ?1";

    protected final Class<T> entityBeanType;

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractRepository() {
        this.entityBeanType = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    @Transactional
    public T create(T model) {
        entityManager.persist(model);
        flushAndClear();
        return model;
    }

    @Override
    @Transactional
    public Optional<T> findById(long id) {
        return Optional.ofNullable(entityManager.find(entityBeanType, id));
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return entityManager
                .createQuery(fillEntityClassInQuery(FIND_ALL_QUERY), entityBeanType)
                .getResultList();
    }

    @Override
    @Transactional
    public T update(T model) {
        return entityManager.merge(model);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        try {
            boolean deleted = entityManager.createQuery(fillEntityClassInQuery(DELETE_BY_ID_QUERY))
                                           .setParameter(1, id)
                                           .executeUpdate() == 1;
            flushAndClear();
            return deleted;
        } catch (PersistenceException ex) {
            return false;
        }
    }

    protected Optional<T> singleParamQuery(String sqlQuery, Object columnValue) {
        String queryWithEntityClass = fillEntityClassInQuery(sqlQuery);
        try {
            return Optional.of(
                    entityManager.createQuery(queryWithEntityClass, entityBeanType)
                                 .setParameter(1, columnValue)
                                 .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    protected List<T> listSingleParamQuery(String sqlQuery, Object columnValue) {
        String queryWithEntityClass = fillEntityClassInQuery(sqlQuery);
        return entityManager.createQuery(queryWithEntityClass, entityBeanType)
                            .setParameter(1, columnValue)
                            .getResultList();
    }

    @Override
    public void flushAndClear() {
        flush();
        clear();
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    protected String fillEntityClassInQuery(String query) {
        return String.format(query, entityBeanType.getSimpleName());
    }

}