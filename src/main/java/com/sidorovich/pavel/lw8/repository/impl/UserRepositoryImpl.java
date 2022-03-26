package com.sidorovich.pavel.lw8.repository.impl;

import com.sidorovich.pavel.lw8.exception.EntityUpdateException;
import com.sidorovich.pavel.lw8.model.City;
import com.sidorovich.pavel.lw8.model.User;
import com.sidorovich.pavel.lw8.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl
        extends AbstractRepository<User>
        implements UserRepository {

    private static final String FIND_BY_IDENTIFICATION_NUMBER = "SELECT U FROM %s U WHERE identificationNumber = ?1";

    private final BeanUtilsBean beanUtilsBean;

    public UserRepositoryImpl(EntityManager entityManager, BeanUtilsBean beanUtilsBean) {
        super(entityManager);
        this.beanUtilsBean = beanUtilsBean;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getCity() != null) {
            user.setCity(attachCity(user.getCity()));
        }
        return super.save(user);
    }

    @Override
    public Optional<User> findByIdentificationNumber(String idNumber) {
        return singleParamQuery(FIND_BY_IDENTIFICATION_NUMBER, idNumber);
    }

    @Override
    public User update(User model) {
        Optional<User> byId = findById(model.getId());
        if (byId.isPresent()) {
            try {
                User userToUpdate = byId.get();
                beanUtilsBean.copyProperties(userToUpdate, model);
                if (model.getCity() != null) {
                    userToUpdate.setCity(attachCity(model.getCity()));
                }
                flushAndClear();
                return byId.get();
            } catch (InvocationTargetException | IllegalAccessException ex) {
                log.error("User update error", ex);
            }
        }
        throw new EntityUpdateException();
    }

    private City attachCity(City city) {
        return entityManager.merge(city);
    }

}
