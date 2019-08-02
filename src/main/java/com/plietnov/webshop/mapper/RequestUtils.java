package com.plietnov.webshop.mapper;

import com.plietnov.webshop.bean.RepositoryBean;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;

public class RequestUtils {

    private static final Logger LOGGER = Logger.getLogger(RequestUtils.class);
    private static final String SHOES_TYPE = "category";
    private static final String MANUFACTURER = "manufacturer";
    private static final String COST_FROM = "cost-from";
    private static final String COST_TO = "cost-to";
    private static final String CURRENT_PAGE = "current-page";
    private static final String ORDER_BY = "order-by";
    private static final String AMOUNT = "amount";
    private static final String PAGE_AMOUNT= "pageAmount";

    public static RepositoryBean requestMapping(HttpServletRequest request) {
        HashMap<String, String[]> filters = new HashMap<>();
        RepositoryBean rb = new RepositoryBean();
        String[] manufacturers = request.getParameterValues(MANUFACTURER);
        if (manufacturers != null) {
            Arrays.stream(manufacturers).forEach(LOGGER::info);
            filters.put(MANUFACTURER, manufacturers);
        }
        String[] shoesType = request.getParameterValues(SHOES_TYPE);
        if (shoesType != null) {
            Arrays.stream(shoesType).forEach(LOGGER::info);
            filters.put(SHOES_TYPE, shoesType);
        }
        rb.setFilter(filters);
        rb.setLoverCost(request.getParameter(COST_FROM));
        rb.setUpperCost(request.getParameter(COST_TO));
        rb.setPageAmount(request.getParameter(PAGE_AMOUNT));
        rb.setCurrentPage(request.getParameter(CURRENT_PAGE));
        rb.setOrderBy(request.getParameter(ORDER_BY));
        rb.setElementPerPage(request.getParameter(AMOUNT));
        return rb;
    }
}
