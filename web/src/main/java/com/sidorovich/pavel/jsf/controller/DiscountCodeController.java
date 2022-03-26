package com.sidorovich.pavel.jsf.controller;

import com.sidorovich.pavel.jsf.filter.DiscountCodeFilter;
import com.sidorovich.pavel.jsf.model.DiscountCode;
import com.sidorovich.pavel.jsf.service.DiscountCodeService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "discountCodeController") // @ManagedBean annotation is deprecated
@SessionScoped
public class DiscountCodeController implements Serializable {

    @EJB
    private DiscountCodeService discountCodeService;

    private final DiscountCodeFilter filter;
    private DiscountCode toCreate;
    private DiscountCode toUpdate;
    private String deleteCode;

    public DiscountCodeController() {
        this.filter = new DiscountCodeFilter();
        this.toCreate = new DiscountCode();
        this.toUpdate = new DiscountCode();
    }

    public List<DiscountCode> getDiscountCodes() {
        return discountCodeService.findByFilter(filter);
    }

    public DiscountCode getToCreate() {
        return toCreate;
    }

    public void setToCreate(DiscountCode toCreate) {
        this.toCreate = toCreate;
    }

    public DiscountCode getToUpdate() {
        return toUpdate;
    }

    public void setToUpdate(DiscountCode toUpdate) {
        this.toUpdate = toUpdate;
    }

    public void setDeleteCode(String deleteCode) {
        this.deleteCode = deleteCode;
    }

    public DiscountCodeFilter getFilter() {
        return filter;
    }

    public String save() {
        discountCodeService.create(toCreate);
        toCreate = new DiscountCode();

        return "discounts?faces-redirect=true";
    }

    public String update() {
        discountCodeService.update(toUpdate);
        toUpdate = new DiscountCode();

        return "discounts?faces-redirect=true";
    }

    public void delete() {
        discountCodeService.deleteByCode(deleteCode.charAt(0));
    }

}
