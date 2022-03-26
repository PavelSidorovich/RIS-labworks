package com.sidorovich.pavel.ris.ejb.service;

import com.sidorovich.pavel.ris.ejb.model.UserAccount;
import jakarta.ejb.Local;

@Local
public interface UserService extends Service<UserAccount> {
}
