package com.sidorovich.pavel.jsf.repository;

import com.sidorovich.pavel.jsf.model.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserRepository extends CrudRepository<User> {

    List<User> findByFilter(Long id, String firstName, String lastName,
                            Integer zip, String email);

}
