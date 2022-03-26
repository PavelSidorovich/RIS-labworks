package com.sidorovich.pavel.lw8.service;

import java.util.List;

public interface ParserService<T> {

    List<T> findAll(String filename);

    void saveAll(List<T> models, String filename);

}
