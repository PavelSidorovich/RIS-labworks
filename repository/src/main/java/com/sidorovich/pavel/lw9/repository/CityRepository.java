package com.sidorovich.pavel.lw9.repository;

import com.sidorovich.pavel.lw9.model.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {

    Optional<CityModel> findByCityNameIgnoreCase(String cityName);

    boolean existsByCityNameIgnoreCase(String cityName);

}
