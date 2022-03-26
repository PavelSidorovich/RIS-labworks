package com.sidorovich.pavel.lw9.util.impl;

import com.sidorovich.pavel.lw9.spec.PageRequestFactory;
import com.sidorovich.pavel.lw9.util.PageRequestFactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageRequestFactoryServiceImpl implements PageRequestFactoryService {

    private final PageRequestFactory pageRequestFactory;

    @Override
    public Pageable pageable(Integer page, Integer size) {
        return pageRequestFactory.pageable(page, size);
    }

}
