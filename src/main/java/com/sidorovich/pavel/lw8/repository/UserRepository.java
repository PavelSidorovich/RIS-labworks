package com.sidorovich.pavel.lw8.repository;

import com.sidorovich.pavel.lw8.model.User;

import java.util.Optional;

public interface UserRepository
        extends CrudRepository<User>, Flushable {

    Optional<User> findByIdentificationNumber(String idNumber);

}
