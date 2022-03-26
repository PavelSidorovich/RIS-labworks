package com.sidorovich.pavel.ris.ejb.service.impl;

import com.sidorovich.pavel.ris.ejb.model.UserAccount;
import com.sidorovich.pavel.ris.ejb.service.UserService;
import jakarta.ejb.Stateless;

@Stateless
public class UserServiceImpl
        extends AbstractService<UserAccount>
        implements UserService {
}
