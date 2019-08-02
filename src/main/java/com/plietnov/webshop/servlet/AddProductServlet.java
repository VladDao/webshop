package com.plietnov.webshop.servlet;

import com.plietnov.webshop.bean.Basket;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.entity.Product;
import com.plietnov.webshop.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddProductServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = (ProductService) this.getServletContext().getAttribute(Constants.PRODUCT_SERVICE);
        HttpSession session = request.getSession();
        Basket basket = (Basket) session.getAttribute("basket");
        if (basket == null) {
            basket = new Basket();
        }
        LOGGER.info(request.getParameter("product-id") + " my input date");
        Product p = productService.getProductById(request.getParameter("product-id"));
        if (basket.containsKey(p)) {
            basket.replace(p, basket.get(p) + 1);
        } else {
            basket.put(p, 1);
        }
        basket.forEach((k, v) -> LOGGER.info(k + "--//--" + v));
        session.setAttribute("basket", basket);
    }
}
