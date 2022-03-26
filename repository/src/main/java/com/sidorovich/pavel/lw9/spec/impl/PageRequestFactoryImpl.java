package com.sidorovich.pavel.lw9.spec.impl;

import com.sidorovich.pavel.lw9.spec.PageRequestFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageRequestFactoryImpl implements PageRequestFactory {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final int MIN_PAGE = 0;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 100;

    @Override
    public Pageable pageable(Integer page, Integer size) {
        return PageRequest.of(getValidPageNumber(page), getValidPageSize(size));
    }

    private int getValidPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < MIN_PAGE
                ? DEFAULT_PAGE : pageNumber;
    }

    private int getValidPageSize(Integer pageSize) {
        return pageSize == null || pageSize < MIN_SIZE || pageSize > MAX_SIZE
                ? DEFAULT_SIZE : pageSize;
    }

}
