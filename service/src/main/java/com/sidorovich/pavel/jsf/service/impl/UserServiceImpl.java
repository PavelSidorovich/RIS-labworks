package com.sidorovich.pavel.jsf.service.impl;

import com.sidorovich.pavel.jsf.filter.UserFilter;
import com.sidorovich.pavel.jsf.model.DiscountCode;
import com.sidorovich.pavel.jsf.model.User;
import com.sidorovich.pavel.jsf.repository.UserRepository;
import com.sidorovich.pavel.jsf.service.DiscountCodeService;
import com.sidorovich.pavel.jsf.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;

@Stateless
public class UserServiceImpl implements UserService {

    private static final String LIKE = "%";

    @EJB
    private UserRepository userRepository;

    @EJB
    private DiscountCodeService discountCodeService;

    @Override
    public List<User> findByFilter(UserFilter filter) {
        final String firstName = buildLikeValue(filter.getFirstName());
        final String lastName = buildLikeValue(filter.getLastName());
        final String email = buildLikeValue(filter.getEmail());

        return userRepository.findByFilter(
                filter.getId(), firstName, lastName,
                filter.getZip(), email
        );
    }

    @Override
    public User create(User model) {
        try {
            Optional<DiscountCode> discount = discountCodeService.findByCode(
                    model.getDiscountCode().getCode().charAt(0)
            );
            discount.ifPresent(model::setDiscountCode);

            return userRepository.create(model);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean delete(long id) {
        return userRepository.delete(id);
    }

    @Override
    public User update(User model) {
        return userRepository.update(model);
    }

    private String buildLikeValue(String value) {
        return value == null || value.isEmpty()
                ? value
                : LIKE + value + LIKE;
    }

}
