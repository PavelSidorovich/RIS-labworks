package com.sidorovich.pavel.jsf.service;

import com.sidorovich.pavel.jsf.filter.DiscountCodeFilter;
import com.sidorovich.pavel.jsf.model.DiscountCode;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface DiscountCodeService extends Service<DiscountCode> {

    List<DiscountCode> findByFilter(DiscountCodeFilter filter);

    Optional<DiscountCode> findByCode(Character code);

    boolean deleteByCode(Character code);

}
