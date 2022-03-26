package com.sidorovich.pavel.lw8.service;

import com.sidorovich.pavel.lw8.model.User;

public interface UserService extends CrudService<User> {

    User findByIdentificationNumber(String idNumber);

}
