package com.sidorovich.pavel.lw8.repository.impl;

import com.sidorovich.pavel.lw8.exception.WiredEntityDeletionException;
import com.sidorovich.pavel.lw8.repository.CrdRepository;
import com.sidorovich.pavel.lw8.repository.Flushable;
import com.sidorovich.pavel.lw8.repository.table.CommonColumn;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T>
        implements CrdRepository<T>, Flushable {

    private static final String FIND_ALL_QUERY = "SELECT m FROM %s m";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM %s WHERE id = ?1";

    protected final Class<T> entityBeanType;

    @PersistenceContext
    protected final EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityBeanType = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    @Transactional
    public T save(T model) {
        entityManager.persist(model);
        flushAndClear();
        return model;
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(entityManager.find(entityBeanType, id));
    }

    @Override
    public List<T> findAll() {
        return entityManager
                .createQuery(fillEntityClassInQuery(FIND_ALL_QUERY), entityBeanType)
                .getResultList();
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
            throw new WiredEntityDeletionException(entityBeanType, CommonColumn.ID.getColumnName(), id);
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
