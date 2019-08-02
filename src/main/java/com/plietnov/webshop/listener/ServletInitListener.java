package com.plietnov.webshop.listener;

import com.plietnov.webshop.bean.CaptchaBean;
import com.plietnov.webshop.connection.TransactionManager;
import com.plietnov.webshop.connection.TransactionManagerImpl;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.dao.BasketDao;
import com.plietnov.webshop.dao.OrderDao;
import com.plietnov.webshop.dao.ProductDao;
import com.plietnov.webshop.dao.UserDao;
import com.plietnov.webshop.dao.CategoryDao;
import com.plietnov.webshop.dao.ManufacturerDao;
import com.plietnov.webshop.service.BasketService;
import com.plietnov.webshop.service.CategoryService;
import com.plietnov.webshop.service.ManufacturerService;
import com.plietnov.webshop.service.OrderService;
import com.plietnov.webshop.service.ProductService;
import com.plietnov.webshop.service.UserService;
import com.plietnov.webshop.strategy.CaptchaStrategyContext;
import com.plietnov.webshop.strategy.CaptchaStrategySession;
import com.plietnov.webshop.thread.CaptchaMonitorThread;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class ServletInitListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ServletInitListener.class);
    private static final String SESSION = "session";
    private static final String CONTEXT = "context";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String strategy = context.getInitParameter("captcha-strategy");
        if (strategy.equals(SESSION)) {
            context.setAttribute(Constants.CAPTCHA_STRATEGY, new CaptchaStrategySession());
        } else if (strategy.equals(CONTEXT)) {
            context.setAttribute(Constants.CAPTCHA_STRATEGY, new CaptchaStrategyContext(context));
        }
        initParam(context);
        runMonitorCaptcha(context);
    }

    private void initParam(ServletContext context) {
        Map<String, String> webMap = new HashMap<>();
        initWebMap(webMap);
        TransactionManager transactionManager = new TransactionManagerImpl(getDataSource());
        UserDao userDao = new UserDao();
        ProductDao productDao = new ProductDao();
        CategoryDao categoryDao = new CategoryDao();
        OrderDao orderDao = new OrderDao();
        ManufacturerDao manufacturerDao = new ManufacturerDao();
        ProductService productService = new ProductService(transactionManager, productDao);
        UserService userService = new UserService(transactionManager, userDao);
        List<CaptchaBean> captchaList = new ArrayList<>();
        BasketDao basketDao = new BasketDao();
        BasketService basketService = new BasketService(basketDao);
        ManufacturerService manufacturerService = new ManufacturerService(transactionManager, manufacturerDao);
        OrderService orderService = new OrderService(orderDao);
        CategoryService categoryService = new CategoryService(transactionManager, categoryDao);
        context.setAttribute(Constants.WEB_MAP, webMap);
        context.setAttribute(Constants.BASKET_SERVICE, basketService);
        context.setAttribute(Constants.MANUFACTURER_SERVICE, manufacturerService);
        context.setAttribute(Constants.TRANSACTION_MANAGER, transactionManager);
        context.setAttribute(Constants.ORDER_SERVICE, orderService);
        context.setAttribute(Constants.CATEGORY_SERVICE, categoryService);
        context.setAttribute(Constants.USER_SERVICE, userService);
        context.setAttribute(Constants.PRODUCT_SERVICE, productService);
        context.setAttribute(Constants.CAPTCHA_LIST, captchaList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    private void runMonitorCaptcha(ServletContext context) {
        Thread thread = new CaptchaMonitorThread(context);
        thread.setDaemon(true);
        thread.start();
    }

    private DataSource getDataSource() {
        DataSource ds = null;
        try {
            Context initContext = new InitialContext();
            Context ctx = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) ctx.lookup("jdbc/postgres");
        } catch (NamingException e) {
            LOGGER.error(e);
        }
        return ds;
    }

    private void initWebMap(Map<String, String> webMap) {
        webMap.put("1", "sneakers");
        webMap.put("2", "shoes");
        webMap.put("3", "slippers");
        webMap.put("11", "nike");
        webMap.put("12", "new-balance");
        webMap.put("13", "converse");
        webMap.put("21", "ORDER BY product_name ASC");
        webMap.put("22", "ORDER BY product_name DESC");
        webMap.put("23", "ORDER BY product_cost ASC");
        webMap.put("24", "ORDER BY product_cost DESC");
        webMap.put("31", "5");
        webMap.put("32", "25");
        webMap.put("33", "50");
        webMap.put("34", "75");
        webMap.put("35", "100");
    }
}
