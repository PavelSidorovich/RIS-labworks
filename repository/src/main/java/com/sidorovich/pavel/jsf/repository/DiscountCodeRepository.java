package com.sidorovich.pavel.jsf.repository;

import com.sidorovich.pavel.jsf.model.DiscountCode;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface DiscountCodeRepository extends CrudRepository<DiscountCode> {

    List<DiscountCode> findByFilter(String code, Integer discount);

    Optional<DiscountCode> findByCode(String code);

    boolean deleteByCode(String code);

}
