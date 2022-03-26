package com.sidorovich.pavel.lw9.spec;

import org.springframework.data.domain.Pageable;

public interface PageRequestFactory {

    Pageable pageable(Integer page, Integer size);

}
