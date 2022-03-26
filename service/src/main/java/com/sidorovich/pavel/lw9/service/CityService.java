package com.sidorovich.pavel.lw9.service;

import com.sidorovich.pavel.lw9.dto.CityDto;

public interface CityService extends CrdService<CityDto> {

    CityDto findByCityName(String cityName);

    boolean existsByCityName(String cityName);

}
