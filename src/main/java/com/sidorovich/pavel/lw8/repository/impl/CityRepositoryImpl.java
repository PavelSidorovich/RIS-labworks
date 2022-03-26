package com.sidorovich.pavel.lw8.repository.impl;

import com.sidorovich.pavel.lw8.model.City;
import com.sidorovich.pavel.lw8.repository.CityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class CityRepositoryImpl
        extends AbstractRepository<City>
        implements CityRepository {

    private static final String FIND_BY_CITY = "SELECT c FROM %s c WHERE cityName = ?1";

    public CityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Transactional
    public Optional<City> findByCity(String city) {
        return singleParamQuery(FIND_BY_CITY, city);
    }

}
