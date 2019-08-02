package com.plietnov.webshop.servlet;

import com.plietnov.webshop.Path;
import com.plietnov.webshop.bean.Basket;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class DefaultServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DefaultServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Basket basket = (Basket) session.getAttribute("basket");
        if (basket == null) {
            basket = new Basket();
        }
        Optional<Integer> res = basket.values().stream().reduce(Integer::sum);
        int size = res.orElse(0);
        LOGGER.info(size);
        request.setAttribute("basketSize", size);
        request.getRequestDispatcher(Path.PAGE_MAIN).forward(request, response);
    }
}
