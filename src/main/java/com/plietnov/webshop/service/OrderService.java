package com.plietnov.webshop.service;

import com.plietnov.webshop.dao.OrderDao;
import com.plietnov.webshop.entity.Order;

import java.util.Random;

public class OrderService {

    private static final int BOUND = 1000000;

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void makeOrder(Order order) {
        orderDao.addOrder(order);
    }

    public int generateOrderId() {
        Random random = new Random();
        return random.nextInt(BOUND);
    }
}
