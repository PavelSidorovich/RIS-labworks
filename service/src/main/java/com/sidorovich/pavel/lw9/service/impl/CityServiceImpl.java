package com.sidorovich.pavel.lw9.service.impl;

import com.sidorovich.pavel.lw9.dto.CityDto;
import com.sidorovich.pavel.lw9.exception.DuplicatePropertyException;
import com.sidorovich.pavel.lw9.exception.EntityNotFoundException;
import com.sidorovich.pavel.lw9.exception.WiredEntityDeletionException;
import com.sidorovich.pavel.lw9.model.CityModel;
import com.sidorovich.pavel.lw9.model.CityModel_;
import com.sidorovich.pavel.lw9.repository.CityRepository;
import com.sidorovich.pavel.lw9.service.CityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CityDto create(CityDto model) {
        final String cityName = model.getCityName();
        if (existsByCityName(cityName)) {
            throw new DuplicatePropertyException(CityDto.class, CityModel_.CITY_NAME, cityName);
        }
        CityModel city = cityRepository.save(modelMapper.map(model, CityModel.class));
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public CityDto findById(long id) {
        CityModel city = cityRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(CityDto.class, CityModel_.ID, id)
        );
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public CityDto findByCityName(String cityName) {
        CityModel city = cityRepository.findByCityNameIgnoreCase(cityName).orElseThrow(
                () -> new EntityNotFoundException(CityDto.class, CityModel_.CITY_NAME, cityName)
        );
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public List<CityDto> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable).getContent().stream()
                             .map(model -> modelMapper.map(model, CityDto.class))
                             .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        try {
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new WiredEntityDeletionException(CityDto.class, CityModel_.ID, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(CityDto.class, CityModel_.ID, id);
        }
    }

    @Override
    public boolean existsByCityName(String cityName) {
        return cityRepository.existsByCityNameIgnoreCase(cityName);
    }

}
