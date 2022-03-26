package com.sidorovich.pavel.ris.ejb.service;

import com.sidorovich.pavel.ris.ejb.model.Cafe;
import com.sidorovich.pavel.ris.ejb.model.CafeFilter;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface CafeService extends Service<Cafe> {

    List<Cafe> findAll();

    List<Cafe> findByCafeType(String type);

    List<Cafe> findByFilter(CafeFilter filter);

}
