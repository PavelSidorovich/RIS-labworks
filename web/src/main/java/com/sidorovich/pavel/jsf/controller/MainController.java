package com.sidorovich.pavel.jsf.controller;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named(value = "mainController") // @ManagedBean annotation is deprecated
@SessionScoped
public class MainController implements Serializable {

    private static final String MAIN_PAGE_PATH = "views/users.xhtml";

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(MAIN_PAGE_PATH);
    }

}
