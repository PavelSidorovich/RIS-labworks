package com.sidorovich.pavel.jsf.repository.impl;

import com.sidorovich.pavel.jsf.model.DiscountCode;
import com.sidorovich.pavel.jsf.model.User;
import com.sidorovich.pavel.jsf.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Stateless
public class UserRepositoryImpl
        extends AbstractRepository<User>
        implements UserRepository {

    private static final String FIND_BY_FILTER =
            "SELECT a " +
            "FROM %s a " +
            "WHERE (:id IS NULL OR CAST(a.id AS varchar(256)) = CAST(:id AS varchar(256))) " +
            "AND (:firstName IS NULL OR a.firstName LIKE :firstName) " +
            "AND (:lastName IS NULL OR a.lastName LIKE :lastName) " +
            "AND (:email IS NULL OR a.email LIKE :email) " +
            "AND (:zip IS NULL OR CAST(a.zip AS varchar(256)) = CAST(:zip AS varchar(256)))";

    @Override
    public List<User> findByFilter(Long id, String firstName, String lastName,
                                   Integer zip, String email) {
        final String query = fillEntityClassInQuery(FIND_BY_FILTER);
        return entityManager.createQuery(query, entityBeanType)
                            .setParameter("id", id)
                            .setParameter("firstName", firstName)
                            .setParameter("lastName", lastName)
                            .setParameter("zip", zip)
                            .setParameter("email", email)
                            .getResultList();
    }

    @Override
    @Transactional
    public User update(User model) {
        final Optional<User> current = findById(model.getId());
        final DiscountCode discountCode = entityManager.find(
                DiscountCode.class, model.getDiscountCode().getCode()
        );
        if (current.isPresent()) {
            final User user = current.get();
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setZip(model.getZip());
            user.setDiscountCode(discountCode);
            return user;
        }
        return null;
    }

}
