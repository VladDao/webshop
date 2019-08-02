package com.plietnov.webshop.dao;

import com.plietnov.webshop.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private List<Order> orders;

    public OrderDao() {
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
