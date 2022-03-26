package com.sidorovich.pavel.jsf.repository.impl;

import com.sidorovich.pavel.jsf.model.DiscountCode;
import com.sidorovich.pavel.jsf.repository.DiscountCodeRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Stateless
public class DiscountCodeRepositoryImpl
        extends AbstractRepository<DiscountCode>
        implements DiscountCodeRepository {

    private static final String DELETE_BY_CODE_QUERY = "DELETE FROM %s WHERE code = ?1";
    private static final String FIND_BY_CODE_QUERY = "SELECT c FROM %s c WHERE c.code = ?1";
    private static final String FIND_BY_FILTER =
            "SELECT d " +
            "FROM %s d " +
            "WHERE (:code IS NULL OR d.code LIKE :code) " +
            "AND (:discount IS NULL OR CAST(d.discount AS varchar(256)) = CAST(:discount AS varchar(256)))";

    @Override
    @Transactional
    public List<DiscountCode> findByFilter(String code, Integer discount) {
        final String query = fillEntityClassInQuery(FIND_BY_FILTER);
        return entityManager.createQuery(query, entityBeanType)
                            .setParameter("code", code)
                            .setParameter("discount", discount)
                            .getResultList();
    }

    @Override
    @Transactional
    public Optional<DiscountCode> findByCode(String code) {
        return singleParamQuery(FIND_BY_CODE_QUERY, code);
    }

    @Override
    public Optional<DiscountCode> findById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public boolean deleteByCode(String code) {
        {
            try {
                boolean deleted = entityManager.createQuery(fillEntityClassInQuery(DELETE_BY_CODE_QUERY))
                                               .setParameter(1, code)
                                               .executeUpdate() == 1;
                flushAndClear();
                return deleted;
            } catch (PersistenceException ex) {
                return false;
            }
        }
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

}