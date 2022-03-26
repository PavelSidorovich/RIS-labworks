package com.sidorovich.pavel.lw8.repository;

import java.util.List;
import java.util.Optional;

public interface CrdRepository<T> {

    T save(T model);

    Optional<T> findById(long id);

    List<T> findAll();

    boolean delete(long id);

}
