package com.plietnov.webshop.servlet;

import com.plietnov.webshop.Path;
import com.plietnov.webshop.bean.Basket;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.entity.Order;
import com.plietnov.webshop.service.BasketService;
import com.plietnov.webshop.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/makeOrder")
public class MakeOrderServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(MakeOrderServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Make order servlet started");
        ServletContext context = this.getServletContext();
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        BasketService basketService = (BasketService) context.getAttribute(Constants.BASKET_SERVICE);
        request.setAttribute(Constants.TOTAL_COST, basketService.getTotalPurchaseAmount(basket));
        OrderService orderService = (OrderService) context.getAttribute(Constants.ORDER_SERVICE);
        Order order = (Order) request.getSession().getAttribute(Constants.ORDER);
        order.setDate(new Date());
        orderService.makeOrder(order);
        request.setAttribute(Constants.ORDER, order);
        BasketService.clearBasket();
        LOG.debug("Make order servlet finished");
        request.getRequestDispatcher(Path.MAKE_ORDER_PAGE).forward(request, response);
    }
}
