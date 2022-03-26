package com.sidorovich.pavel.ris.ejb.service.impl;

import com.sidorovich.pavel.ris.ejb.model.Cafe;
import com.sidorovich.pavel.ris.ejb.model.CafeFilter;
import com.sidorovich.pavel.ris.ejb.model.CafeType;
import com.sidorovich.pavel.ris.ejb.service.CafeService;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class CafeServiceImpl
        extends AbstractService<Cafe>
        implements CafeService {

    private static final String FIND_ALL_QUERY = "SELECT c FROM Cafe c";
    private static final String FIND_BY_TYPE_QUERY = "SELECT c FROM Cafe c WHERE c.type = ?1";

    @PersistenceContext(unitName = "default")
    public EntityManager entityManager;

    @Override
    public List<Cafe> findAll() {
        return entityManager.createQuery(FIND_ALL_QUERY, Cafe.class)
                            .getResultList();
    }

    @Override
    public List<Cafe> findByCafeType(String type) {
        return entityManager.createQuery(FIND_BY_TYPE_QUERY, Cafe.class)
                            .setParameter(1, CafeType.valueOf(type))
                            .getResultList();
    }

    @Override
    public List<Cafe> findByFilter(CafeFilter filter) {
        String sqlQuery = "SELECT c FROM Cafe c WHERE ";
        int conditionCounter = 1;
        if (!filter.getCafeType().equals(CafeType.ALL)) {
            sqlQuery += "c.type = ?" + conditionCounter++ + " AND ";
        }
        sqlQuery += "c.avgCost >= ?" + conditionCounter++ + " AND "
                    + "c.avgCost <= ?" + conditionCounter;
        TypedQuery<Cafe> query = entityManager.createQuery(sqlQuery, Cafe.class);
        return conditionCounter > 2
                ? query.setParameter(1, filter.getCafeType())
                       .setParameter(2, filter.getMinCost())
                       .setParameter(3, filter.getMaxCost())
                       .getResultList()
                : query.setParameter(1, filter.getMinCost())
                       .setParameter(2, filter.getMaxCost())
                       .getResultList();
    }

}
