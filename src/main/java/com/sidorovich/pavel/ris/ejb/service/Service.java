package com.sidorovich.pavel.ris.ejb.service;

import java.util.Optional;

public interface Service<T> {

    Optional<T> findById(long id);

}
