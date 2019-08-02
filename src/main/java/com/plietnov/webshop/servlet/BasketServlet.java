package com.plietnov.webshop.servlet;

import com.plietnov.webshop.Path;
import com.plietnov.webshop.bean.Basket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/showBasket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Basket basket = (Basket) session.getAttribute("basket");
        if (basket == null) {
            basket = new Basket();
        }
        session.setAttribute("basket", basket);
        request.getRequestDispatcher(Path.PAGE_SHOW_BASKET).forward(request, response);
    }
}
