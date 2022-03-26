package com.sidorovich.pavel.lw8.service;

import java.util.List;

public interface CrdService<T> {

    T save(T model);

    List<T> findAll();

    T findById(long id);

    void delete(long id);

}
