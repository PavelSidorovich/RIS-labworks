package com.sidorovich.pavel.lw8.service;

public interface CrudService<T> extends CrdService<T> {

    T update(T model);

}
