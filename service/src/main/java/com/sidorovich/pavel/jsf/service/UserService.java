package com.sidorovich.pavel.jsf.service;

import com.sidorovich.pavel.jsf.filter.UserFilter;
import com.sidorovich.pavel.jsf.model.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService extends Service<User> {

    List<User> findByFilter(UserFilter filter);

}
