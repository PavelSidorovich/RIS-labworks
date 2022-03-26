package com.sidorovich.pavel.lw8.repository;

public interface CrudRepository<T> extends CrdRepository<T> {

    T update(T model);

}
