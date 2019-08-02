package com.plietnov.webshop.service;

import com.plietnov.webshop.bean.Basket;
import com.plietnov.webshop.dao.BasketDao;
import com.plietnov.webshop.entity.Clothes;
import com.plietnov.webshop.entity.Product;

import java.util.Map;

public class BasketService {

    private BasketDao basketDao;

    public BasketService(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    public BasketService() {
    }

    public double getTotalPurchaseAmount(Basket basket) {
        double totalPurchaseAmount = 0;
        for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
            totalPurchaseAmount += Double.parseDouble(((Clothes) entry.getKey()).getCost()) * entry.getValue();
        }
        return totalPurchaseAmount;
    }

    public static void clearBasket() {
    }

    public int getProductsNumber() {
        return basketDao.getProductsInBasket()
                .values()
                .stream()
                .mapToInt(i -> i)
                .sum();
    }

    public void clearBaket() {
        basketDao.clear();
    }

    public void removeFromBasket(Product product) {
        basketDao.removeFromBasket(product);
    }
}
