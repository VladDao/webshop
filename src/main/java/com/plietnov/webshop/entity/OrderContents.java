package com.plietnov.webshop.entity;

import java.util.Map;

public class OrderContents {
    private Map<Product, Integer> products;

    public OrderContents(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}
