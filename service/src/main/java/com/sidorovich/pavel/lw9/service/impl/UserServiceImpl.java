package com.sidorovich.pavel.lw9.service.impl;

import com.sidorovich.pavel.lw9.dto.CityDto;
import com.sidorovich.pavel.lw9.dto.UserDto;
import com.sidorovich.pavel.lw9.exception.DuplicatePropertyException;
import com.sidorovich.pavel.lw9.exception.EntityNotFoundException;
import com.sidorovich.pavel.lw9.exception.WiredEntityDeletionException;
import com.sidorovich.pavel.lw9.model.CityModel;
import com.sidorovich.pavel.lw9.model.UserModel;
import com.sidorovich.pavel.lw9.model.UserModel_;
import com.sidorovich.pavel.lw9.repository.UserRepository;
import com.sidorovich.pavel.lw9.service.CityService;
import com.sidorovich.pavel.lw9.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CityService cityService;
    private final ModelMapper modelMapper;
    private final ModelMapper userUpdateMapper;

    @Override
    @Transactional
    public UserDto create(UserDto user) {
        final String idNumber = user.getIdNumber();

        if (userRepository.existsByIdNumberIgnoreCase(idNumber)) {
            throw new DuplicatePropertyException(UserDto.class, UserModel_.ID_NUMBER, idNumber);
        }
        UserModel userModel = modelMapper.map(user, UserModel.class);
        userModel.setCity(prepareCity(user.getCity()));

        return modelMapper.map(userRepository.save(userModel), UserDto.class);
    }

    @Override
    public UserDto findById(long id) {
        UserModel certificate = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(UserDto.class, UserModel_.ID, id)
        );
        return modelMapper.map(certificate, UserDto.class);
    }

    @Override
    public List<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent().stream()
                             .map(user -> modelMapper.map(user, UserDto.class))
                             .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto update(UserDto user) {
        Optional<UserModel> optCert = userRepository.findById(user.getId());
        UserModel userToUpdate = optCert.orElseThrow(() -> new EntityNotFoundException(
                UserDto.class, UserModel_.ID, user.getId())
        );
        CityModel preparedCity = prepareCity(user.getCity());
        userUpdateMapper.map(user, userToUpdate);
        if (preparedCity != null) {
            userToUpdate.setCity(preparedCity);
        }
        return modelMapper.map(userRepository.save(userToUpdate), UserDto.class);
    }

    private CityModel prepareCity(CityDto city) {
        return city == null? null
                : modelMapper.map(cityService.existsByCityName(city.getCityName())
                                          ? cityService.findByCityName(city.getCityName())
                                          : cityService.create(city), CityModel.class);

    }

    @Override
    public void delete(long id) {
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new WiredEntityDeletionException(UserDto.class, UserModel_.ID, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(UserDto.class, UserModel_.ID, id);
        }
    }

}
