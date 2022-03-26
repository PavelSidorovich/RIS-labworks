package com.sidorovich.pavel.jsf.controller;

import com.sidorovich.pavel.jsf.filter.UserFilter;
import com.sidorovich.pavel.jsf.model.DiscountCode;
import com.sidorovich.pavel.jsf.model.User;
import com.sidorovich.pavel.jsf.service.DiscountCodeService;
import com.sidorovich.pavel.jsf.service.UserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "userController") // @ManagedBean annotation is deprecated
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private DiscountCodeService discountCodeService;

    private final UserFilter filter;
    private final DiscountCode discountCode;
    private User toCreate;
    private User toUpdate;
    private String deleteId;

    public UserController() {
        this.filter = new UserFilter();
        this.discountCode = new DiscountCode();
        this.toCreate = new User();
        this.toUpdate = new User();
    }

    public List<User> getUsers() {
        return userService.findByFilter(filter);
    }

    public List<DiscountCode> getDiscountCodes() {
        return discountCodeService.findAll();
    }

    public User getToCreate() {
        return toCreate;
    }

    public void setToCreate(User toCreate) {
        this.toCreate = toCreate;
    }

    public User getToUpdate() {
        return toUpdate;
    }

    public void setToUpdate(User toUpdate) {
        this.toUpdate = toUpdate;
    }

    public void setDeleteId(String deleteId) {
        this.deleteId = deleteId;
    }

    public UserFilter getFilter() {
        return filter;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public String save() {
        toCreate.setDiscountCode(discountCode);
        userService.create(toCreate);
        toCreate = new User();

        return "users?faces-redirect=true";
    }

    public String update() {
        toUpdate.setDiscountCode(discountCode);
        userService.update(toUpdate);
        toUpdate = new User();

        return "users?faces-redirect=true";
    }

    public void delete() {
        try {
            final long id = Long.parseLong(deleteId);
            userService.delete(id);
        } catch (Exception ignored) {
        }
    }

}
