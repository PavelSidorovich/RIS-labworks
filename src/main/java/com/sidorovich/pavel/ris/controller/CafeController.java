package com.sidorovich.pavel.ris.controller;

import com.sidorovich.pavel.ris.ejb.model.Cafe;
import com.sidorovich.pavel.ris.ejb.model.CafeType;
import com.sidorovich.pavel.ris.ejb.service.CafeService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CafeController", urlPatterns = "/cafes/type")
public class CafeController extends HttpServlet {

    private static final String THIS_PAGE_PATH = "/WEB-INF/view/main.jsp";
    private static final String CAFE_TYPE_PARAM = "cafeType";
    private static final String CAFES_JSP_ATTR = "cafes";
    private static final String FILTER_JSP_ATTR = "filter";

    @EJB
    private CafeService cafeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cafeType = req.getParameter(CAFE_TYPE_PARAM);
        RequestDispatcher dispatcher = req.getRequestDispatcher(THIS_PAGE_PATH);
        List<Cafe> cafes = (cafeType == null || CafeType.ALL.name().equals(cafeType))
                ? cafeService.findAll()
                : cafeService.findByCafeType(cafeType);

        if (cafeType != null) {
            req.setAttribute(FILTER_JSP_ATTR, CafeType.valueOf(cafeType).getType());
        }
        req.setAttribute(CAFES_JSP_ATTR, cafes);
        dispatcher.forward(req, resp);
    }

}
