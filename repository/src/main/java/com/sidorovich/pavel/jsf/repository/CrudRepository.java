package com.sidorovich.pavel.jsf.repository;

import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface CrudRepository<T> {

    T create(T model);

    Optional<T> findById(long id);

    List<T> findAll();

    boolean delete(long id);

    T update(T model);

}
