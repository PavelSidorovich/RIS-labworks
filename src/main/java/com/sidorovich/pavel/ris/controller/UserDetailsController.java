package com.sidorovich.pavel.ris.controller;

import com.sidorovich.pavel.ris.ejb.exception.EntityNotFoundException;
import com.sidorovich.pavel.ris.ejb.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "UserDetailsController", urlPatterns = "/users")
public class UserDetailsController extends HttpServlet {

    private static final String USER_JSP_ATTR = "user";
    private static final String ID_REQUEST_PARAM = "userId";
    private static final String CURRENT_PAGE_PATH = "/WEB-INF/view/user.jsp";

    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter(ID_REQUEST_PARAM));
            req.setAttribute(
                    USER_JSP_ATTR,
                    userService.findById(id)
                               .orElseThrow(EntityNotFoundException::new)
            );
        } catch (Exception ex) {
            log.error("something went wrong", ex);
        }
        req.getRequestDispatcher(CURRENT_PAGE_PATH).forward(req, resp);
    }

}
