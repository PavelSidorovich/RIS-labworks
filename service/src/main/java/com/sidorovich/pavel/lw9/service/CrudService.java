package com.sidorovich.pavel.lw9.service;

public interface CrudService<T> extends CrdService<T> {

    T update(T model);

}
