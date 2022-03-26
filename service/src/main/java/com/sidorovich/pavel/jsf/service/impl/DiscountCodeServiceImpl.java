package com.sidorovich.pavel.jsf.service.impl;

import com.sidorovich.pavel.jsf.filter.DiscountCodeFilter;
import com.sidorovich.pavel.jsf.model.DiscountCode;
import com.sidorovich.pavel.jsf.repository.DiscountCodeRepository;
import com.sidorovich.pavel.jsf.service.DiscountCodeService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;

@Stateless
public class DiscountCodeServiceImpl implements DiscountCodeService {

    @EJB
    private DiscountCodeRepository discountCodeRepository;

    @Override
    public DiscountCode create(DiscountCode model) {
        try {
            return discountCodeRepository.create(model);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DiscountCode> findByFilter(DiscountCodeFilter filter) {
        return discountCodeRepository.findByFilter(filter.getCode(), filter.getDiscount());
    }

    @Override
    public Optional<DiscountCode> findByCode(Character code) {
        return discountCodeRepository.findByCode(String.valueOf(code));
    }

    @Override
    public Optional<DiscountCode> findById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DiscountCode> findAll() {
        return discountCodeRepository.findAll();
    }

    @Override
    public DiscountCode update(DiscountCode model) {
        return discountCodeRepository.update(model);
    }

    @Override
    public boolean deleteByCode(Character code) {
        return discountCodeRepository.deleteByCode(String.valueOf(code));
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

}
