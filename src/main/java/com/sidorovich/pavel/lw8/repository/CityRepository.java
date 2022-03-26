package com.sidorovich.pavel.lw8.repository;

import com.sidorovich.pavel.lw8.model.City;

import java.util.Optional;

public interface CityRepository extends CrdRepository<City> {

    Optional<City> findByCity(String city);

}
