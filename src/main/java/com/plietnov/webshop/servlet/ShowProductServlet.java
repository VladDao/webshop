package com.plietnov.webshop.servlet;

import com.plietnov.webshop.Path;
import com.plietnov.webshop.bean.RepositoryBean;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.mapper.RequestUtils;
import com.plietnov.webshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/ShowProduct")
public class ShowProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepositoryBean repositoryBean = RequestUtils.requestMapping(request);
        ProductService productService = (ProductService) this.getServletContext().getAttribute(Constants.PRODUCT_SERVICE);
        Map<String, String> webMap = (Map<String, String>) this.getServletContext().getAttribute(Constants.WEB_MAP);
        repositoryBean.setProductList(productService.getProducts(webMap, repositoryBean));
        request.setAttribute("repositoryBean", repositoryBean);
        request.getRequestDispatcher(Path.PAGE_SHOW_PRODUCT).forward(request, response);
    }
}
