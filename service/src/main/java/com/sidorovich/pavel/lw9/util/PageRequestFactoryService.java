package com.sidorovich.pavel.lw9.util;

import org.springframework.data.domain.Pageable;

public interface PageRequestFactoryService {

    Pageable pageable(Integer page, Integer size);

}
