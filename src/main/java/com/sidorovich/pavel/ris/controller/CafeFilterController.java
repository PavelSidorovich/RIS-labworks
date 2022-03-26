package com.sidorovich.pavel.ris.controller;

import com.sidorovich.pavel.ris.ejb.model.Cafe;
import com.sidorovich.pavel.ris.ejb.model.CafeFilter;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@WebServlet(name = "CafeFilterController", urlPatterns = "/cafes/filter")
public class CafeFilterController extends HttpServlet {

    private static final String FILTER_MESSAGE = "%s, min cost - $%.2f, max cost - $%.2f";
    private static final String FILTER_JSP_ATTR = "filter";
    private static final String MAX_COST_PARAM = "maxCost";
    private static final String MIN_COST_PARAM = "minCost";
    private static final String CURRENT_PAGE_PATH = "/WEB-INF/view/filter.jsp";
    private static final String CAFES_JSP_ATTR = "cafes";
    private static final int COST_SCALE = 2;

    @EJB
    private CafeService cafeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CafeFilter filter = getCafeFilter(req);
        List<Cafe> cafes = cafeService.findByFilter(filter);

        req.setAttribute(CAFES_JSP_ATTR, cafes);
        RequestDispatcher dispatcher = req.getRequestDispatcher(CURRENT_PAGE_PATH);
        dispatcher.forward(req, resp);
    }

    private CafeFilter getCafeFilter(HttpServletRequest req) {
        CafeType ct = getCafeType(req);
        String minCost = req.getParameter(MIN_COST_PARAM);
        String maxCost = req.getParameter(MAX_COST_PARAM);
        BigDecimal minCostFilter = getCost(minCost);
        BigDecimal maxCostFilter = getCost(maxCost);
        req.setAttribute(FILTER_JSP_ATTR, String.format(
                FILTER_MESSAGE, ct.getType(),
                minCostFilter.setScale(COST_SCALE, RoundingMode.CEILING),
                maxCostFilter.setScale(COST_SCALE, RoundingMode.CEILING))
        );

        return new CafeFilter(ct, minCostFilter, maxCostFilter);
    }

    private CafeType getCafeType(HttpServletRequest req) {
        String cafeType = req.getParameter("cafeType");
        return (cafeType == null)
                ? CafeType.ALL
                : CafeType.valueOf(cafeType);
    }

    private BigDecimal getCost(String maxCost) {
        return (maxCost == null || maxCost.isEmpty())
                ? BigDecimal.ZERO
                : new BigDecimal(maxCost);
    }

}