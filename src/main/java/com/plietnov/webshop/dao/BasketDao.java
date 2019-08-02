package com.plietnov.webshop.dao;

import com.plietnov.webshop.bean.Basket;
import com.plietnov.webshop.entity.Product;

public class BasketDao {

    private Basket productsInBasket;

    public BasketDao() {
        productsInBasket = new Basket();
    }

    public Basket getProductsInBasket() {
        return productsInBasket;
    }

    public void addToBasket(Product product) {
        int newAmount = 1;
        if (productsInBasket.containsKey(product)) {
            newAmount += productsInBasket.get(product);
        }
        productsInBasket.put(product, newAmount);
    }

    public void removeFromBasket(Product product) {
        productsInBasket.remove(product);
    }

    public void clear() {
        productsInBasket.clear();
    }
}
