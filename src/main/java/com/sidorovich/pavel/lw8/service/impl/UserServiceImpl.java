package com.sidorovich.pavel.lw8.service.impl;

import com.sidorovich.pavel.lw8.exception.DuplicatePropertyException;
import com.sidorovich.pavel.lw8.exception.EntityNotFoundException;
import com.sidorovich.pavel.lw8.model.City;
import com.sidorovich.pavel.lw8.model.User;
import com.sidorovich.pavel.lw8.repository.CityRepository;
import com.sidorovich.pavel.lw8.repository.UserRepository;
import com.sidorovich.pavel.lw8.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Override
    @Transactional
    public User save(User user) {
        final String identificationNumber = user.getIdentificationNumber();

        if (userRepository.findByIdentificationNumber(identificationNumber).isPresent()) {
            throw new DuplicatePropertyException(
                    User.class, "identificationNumber", identificationNumber
            );
        }
        user.setCity(prepareCityToMerge(user.getCity()));
        User created = userRepository.save(user);
        userRepository.flushAndClear();
        return created;
    }

    private City prepareCityToMerge(City city) {
        return cityRepository.findByCity(city.getCityName())
                             .orElseGet(() -> cityRepository.save(city));
    }

    @Override
    @Transactional
    public User update(User model) {
        final User byId = userRepository.findById(model.getId()).orElseThrow(
                () -> new EntityNotFoundException(User.class, "id", model.getId())
        );
        model.setId(byId.getId());
        model.setCity(prepareCityToMerge(model.getCity()));

        return userRepository.update(model);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        final List<User> users = userRepository.findAll();
        userRepository.clear();
        return users;
    }

    @Override
    public User findById(long id) {
        User certificate = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(User.class, "id", id)
        );
        userRepository.clear();
        return certificate;
    }

    @Override
    public User findByIdentificationNumber(String idNumber) {
        final User user = findByIdNumberWithoutClear(idNumber);
        userRepository.clear();
        return user;
    }

    private User findByIdNumberWithoutClear(String idNumber) {
        return userRepository.findByIdentificationNumber(idNumber).orElseThrow(
                () -> new EntityNotFoundException(User.class, "identificationNumber", idNumber)
        );
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!userRepository.delete(id)) {
            throw new EntityNotFoundException(User.class, "id", id);
        }
        userRepository.flushAndClear();
    }

}
