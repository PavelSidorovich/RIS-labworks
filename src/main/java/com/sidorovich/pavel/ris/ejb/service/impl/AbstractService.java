package com.sidorovich.pavel.ris.ejb.service.impl;

import com.sidorovich.pavel.ris.ejb.service.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

public abstract class AbstractService<T> implements Service<T> {

    protected final Class<T> entityBeanType;

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractService() {
        this.entityBeanType = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(entityManager.find(entityBeanType, id));
    }

}
